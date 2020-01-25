package com.sdgt.medcare.master.async;

import com.sdgt.medcare.master.constants.ElasticConstants;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import com.sdgt.medcare.master.entity.BaseMaster;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Elastic search assistant integration call.
 * <p>
 * SD Global Technologies. Copyright (c) 2019.
 * All rights reserved.
 *
 * @author Nitesh Kumar
 */

@Configuration
@EnableAsync
@Component
public class ElasticSearchAssistant<T> {
    private Logger logger = LoggerFactory.getLogger(ElasticSearchAssistant.class);
    private static final String AUDIT_TRAIL_PATH = "/auditTrail/";
    private String audit_service = "audit-service";
    private final static String URI_PROTOCOL = "http://";
    private final static String URI_PROTOCOLS = "https://";
    private final static String URI_DELIMITER = ":";

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private EurekaClient eurekaClient;

    /**
     * Sync new object to Elastic search server.
     *
     * @param resourceObject a valid object.
     * @param isUpdate
     * @throws Exception if any error.
     */
    @Async
    public void syncWithElasticServer(final T resourceObject, String objectName, boolean isUpdate, boolean isGenericMaster) throws JsonProcessingException {
        logger.info("The Service ID : " + audit_service);
        Application application = eurekaClient.getApplication(audit_service);
        logger.info("eureka client " + eurekaClient + " property " + audit_service);
        logger.info("Application client " + application.getName() + " application Instance size " + application.getInstances().size());

        final InstanceInfo instanceInfo = application.getInstances().get(0);
        RestTemplate restTemplateForElastic = new RestTemplate();
        String auditTrailDataObject = prepareAuditTrailDataObject(resourceObject, objectName, isUpdate, isGenericMaster);
        logger.info("Request Object for Audit trail: " + auditTrailDataObject);
        String responseFromElastic = "";
        String apiOpearation = "";
        if (isUpdate) {
            if(resourceObject instanceof BaseMaster){
                String objectId = ((BaseMaster) resourceObject).getId();
                apiOpearation = "/auditTrail/" + trimPatientId(objectName+objectId);
            }
            logger.info("Elastic API url:" + prepareURIForRest(instanceInfo, apiOpearation));
            restTemplateForElastic.put(prepareURIForRest(instanceInfo, apiOpearation), auditTrailDataObject);
        } else {
            logger.info("Elastic API url:" + prepareURIForRest(instanceInfo, "/auditTrail/"));
            responseFromElastic = restTemplateForElastic.postForObject(prepareURIForRest(instanceInfo, "/auditTrail/"),
                    auditTrailDataObject, String.class);
            logger.info("Response from Elastic: " + responseFromElastic);
        }
    }

    private String trimPatientId(final String patientId) {
        return patientId.replace("ExtendedPatient/", "");
    }

    /**
     * Prepare the Order transaction URI.
     *
     * @param instanceInfo an object instance of type {@link InstanceInfo}.
     * @param apiOperation API Operation service path.
     * @return a {@link String} value.
     */
    private String prepareURIForRest(final InstanceInfo instanceInfo, final String apiOperation) {
        return (URI_PROTOCOL + instanceInfo.getIPAddr() + URI_DELIMITER + instanceInfo.getPort() + apiOperation);
    }

    /**
     *Prepare audit trail object for FHIR and Non-fhir object
     *
     * @param object
     * @param update
     * @return auditTrailObject
     */
    private String prepareAuditTrailDataObject(T object, String objectName, boolean update, boolean isGenericMaster) throws JsonProcessingException {
        JsonObject auditTrailObject = new JsonObject();
        JsonObject filterObject = new JsonObject();
        if(update){
            if(object instanceof BaseMaster){
                String objectId = ((BaseMaster) object).getId();
                auditTrailObject.addProperty(ElasticConstants.ID, objectName+objectId);
                auditTrailObject.addProperty(ElasticConstants.CREATED_BY, String.valueOf(((BaseMaster) object).getCreatedBy()));
                auditTrailObject.addProperty(ElasticConstants.UPDATED_BY, String.valueOf(((BaseMaster) object).getUpdatedBy()));
            }

            auditTrailObject.addProperty(ElasticConstants.REQUEST_TYPE, String.valueOf(HttpMethod.PUT));
            auditTrailObject.addProperty(ElasticConstants.ACTION, ElasticConstants.UPDATED_ACTION);
            auditTrailObject.addProperty(ElasticConstants.RESPONSE,ElasticConstants.UPDATED_RESPONSE);
            auditTrailObject.addProperty(ElasticConstants.RESPONSE_CODE, String.valueOf(HttpStatus.OK.value()));
        }
        else{
            if(object instanceof BaseMaster){
                String objectId = ((BaseMaster) object).getId();
                auditTrailObject.addProperty(ElasticConstants.ID, trimPatientId(objectName+objectId));
                auditTrailObject.addProperty(ElasticConstants.CREATED_BY, String.valueOf(((BaseMaster) object).getCreatedBy()));
            }
            auditTrailObject.addProperty(ElasticConstants.REQUEST_TYPE,String.valueOf(HttpMethod.POST));
            auditTrailObject.addProperty(ElasticConstants.ACTION,ElasticConstants.CREATED_ACTION);
            auditTrailObject.addProperty(ElasticConstants.RESPONSE,ElasticConstants.CREATED_RESPONSE);
            auditTrailObject.addProperty(ElasticConstants.RESPONSE_CODE, String.valueOf(HttpStatus.CREATED.value()));
        }

        if(!isGenericMaster){
            //TODO set unit code and org code
            //auditTrailObject.addProperty(ElasticConstants.UNIT_CODE, String.valueOf(((AbstractBaseEntity) object).getUnitCode()));
            //auditTrailObject.addProperty(ElasticConstants.ORG_CODE, String.valueOf(((AbstractBaseEntity) object).getOrgCode()));
            auditTrailObject.addProperty(ElasticConstants.UNIT_CODE, "");
            auditTrailObject.addProperty(ElasticConstants.ORG_CODE, "");
        }
        else{
            auditTrailObject.addProperty(ElasticConstants.UNIT_CODE, "");
            auditTrailObject.addProperty(ElasticConstants.ORG_CODE, "");
        }
        filterObject = setFilterObjectData(object);
        auditTrailObject.add("filter", filterObject);
        auditTrailObject.addProperty(ElasticConstants.SERVICE_NAME, "masters-service");
        auditTrailObject.addProperty(ElasticConstants.OBJECT_NAME, objectName);
        auditTrailObject.addProperty(ElasticConstants.ENV_NAME,"Dev");
        String extendedPatientResponseJson = objectMapper.writeValueAsString(object);
        auditTrailObject.addProperty(ElasticConstants.RAW_TEXT, extendedPatientResponseJson);

        return auditTrailObject.toString();
    }

    private JsonObject setFilterObjectData(T object) {
        JsonObject filterJsonObject = new JsonObject();
        if(object instanceof BaseMaster){
            filterJsonObject.addProperty("masterId", ((BaseMaster) object).getId());
            filterJsonObject.addProperty("masterCode", ((BaseMaster) object).getCode());
            filterJsonObject.addProperty("masterDesc", ((BaseMaster) object).getDesc());
        }
        return filterJsonObject;
    }
}
