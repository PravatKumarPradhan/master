
package com.sdgt.medcare.master.controller.common;

import com.core.exceptions.DataException;
import com.core.model.BaseEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sdgt.medcare.master.async.ElasticSearchAssistant;
import com.sdgt.medcare.master.customAnnotations.MastersFieldCustomAnnotation;
import com.sdgt.medcare.master.dto.classProperties.ClassProperty;
import com.sdgt.medcare.master.dto.common.ItemUomRequest;
import com.sdgt.medcare.master.dto.common.ItemsParameterRequest;
import com.sdgt.medcare.master.dto.common.ItemsRequest;
import com.sdgt.medcare.master.dto.common.MultipleIdRequest;
import com.sdgt.medcare.master.dto.common.PaginatedResponseDTO;
import com.sdgt.medcare.master.dto.common.StockTakeItemRequest;
import com.sdgt.medcare.master.dto.common.StoresRequest;
import com.sdgt.medcare.master.entity.AbstractBaseEntity;
import com.sdgt.medcare.master.entity.BaseMaster;
import com.sdgt.medcare.master.exeception.ConflictException;
import com.sdgt.medcare.master.util.AbstractJpaDao;
import com.sdgt.medcare.master.util.BaseMasterJsonStructure;
import com.sdgt.medcare.master.util.ClassFinder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@RestController
@RequestMapping ("/Masters")
@CrossOrigin
public class GenericMasterController {

   @Autowired
   private AbstractJpaDao dao;

   @Autowired
   private ElasticSearchAssistant elasticSearchAssistant;

   protected static ObjectMapper om = new ObjectMapper();

   private Logger logger = LoggerFactory.getLogger(GenericMasterController.class);

   public GenericMasterController() {
      this.dao = new AbstractJpaDao();
   }

   private List<ClassProperty> baseClassProperties;

   static Map<String, List<String>> classPackageMap;

   public static Map<String, List<String>> classPackageMapBase;

   static Map<String, Class<?>> classes;

   @GetMapping (path = "/{classname}/query")
   public ResponseEntity getObjectByQuery(@PathVariable String classname,
                                          @RequestParam (name = "field", required = false) String field,
                                          @RequestParam (name = "value", required = false) String value) {

      Object one = dao.findByQuery(getDomainEntityClass(classname), field, value);
      if (one == null) {
         return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
      }
      return ResponseEntity.ok(one);
   }

   @GetMapping (path = "/{classname}/autosearch")
   public ResponseEntity getAutoSearch(@PathVariable String classname,
                                       @RequestParam (name = "field", required = false) String field,
                                       @RequestParam (name = "value", required = false) String value) {

      Object one = dao.findByAutoSearch(getDomainEntityClass(classname), field, value);
      if (one == null) {
         return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
      }
      return ResponseEntity.ok(one);
   }

   @GetMapping (path = "/{classname}/multiQuery")
   public ResponseEntity getMultiQueryData(@PathVariable String classname,
                                           @RequestParam (name = "field", required = false) String field,
                                           @RequestParam (name = "value", required = false) List<Long> value) {

      Object one = dao.findByMultiQuery(getDomainEntityClass(classname), field, value);
      if (one == null) {
         return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
      }
      return ResponseEntity.ok(one);
   }

   @GetMapping (path = "/{classname}/multiQueryCode")
   public ResponseEntity getMultiQueryDataByCode(@PathVariable String classname,
                                                 @RequestParam (name = "field", required = false) String field,
                                                 @RequestParam (name = "value", required = false) List<String> value) {

      Object one = dao.findByMultiQueryByCode(getDomainEntityClass(classname), field, value);
      if (one == null) {
         return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
      }
      return ResponseEntity.ok(one);
   }

   @PostMapping (value = {"/{classname}/q", "/{classname}/query"}, consumes = {MediaType.TEXT_PLAIN_VALUE,
         MediaType.APPLICATION_JSON_VALUE})
   public List<Object> getObjectByOneQuery(@PathVariable String classname, @RequestBody String json, @RequestParam (value = "likeSearch", defaultValue = "false", required = false) Boolean likeSearch) {
      Boolean clause = (likeSearch) ? likeSearch : false;
      return dao.findByOneQuery(getDomainEntityClass(classname), json, likeSearch);
   }


   /* Add By Predikly Team for dropdown search */
   @RequestMapping (value = {"/{classname}/like"}, method = RequestMethod.POST, consumes = {
         MediaType.TEXT_PLAIN_VALUE, MediaType.APPLICATION_JSON_VALUE})
   public List<Object> getObjectByLikeQuery(@PathVariable String classname, @RequestBody String json) {

      return dao.findByLikeQuery(getDomainEntityClass(classname), json);
   }

   @RequestMapping (path = "/{classname}/{id}")
   public ResponseEntity getObject(@PathVariable String classname, @PathVariable Long id) {
      Object one = dao.findOne(getDomainEntityClass(classname), id);
      if (one == null) {
         one = dao.findOneByCode(getDomainEntityClass(classname), id.toString());
      } else if (one == null) {
         return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
      }
      return ResponseEntity.ok(one);
   }

   @RequestMapping (path = "/{classname}/byCode/{code}")
   public ResponseEntity getObjectByCode(@PathVariable String classname, @PathVariable (value = "code", required = true) String code) {
      Object one = dao.findOneByCode(getDomainEntityClass(classname), code);
      if (one == null) {
         return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
      }
      return ResponseEntity.ok(one);
   }


   @RequestMapping (path = {"/{classname}", "/{classname}/"})
   public ResponseEntity<List> listAll(@PathVariable String classname) {
      List list = dao.findAll(getDomainEntityClass(classname));
      ResponseEntity<List> listResponse = ResponseEntity.ok(list);
      return listResponse;
   }

   @RequestMapping (path = {"/{classname}/all", "/{classname}/All"})
   public ResponseEntity<PaginatedResponseDTO> listAllwithInactive(@PathVariable String classname
         , @RequestParam (value = "offset", required = false) Long offset, @RequestParam (value = "limit", required = false) Long limit) {
      PaginatedResponseDTO paginatedResponseDTO = dao.findAllIncludingInactive(getDomainEntityClass(classname), offset, limit);
      ResponseEntity<PaginatedResponseDTO> listResponse = ResponseEntity.ok(paginatedResponseDTO);
      return listResponse;
   }

   @RequestMapping (value = {"/{classname}/list", "/{classname}/List"}, method = RequestMethod.POST, consumes = {
         MediaType.TEXT_PLAIN_VALUE, MediaType.APPLICATION_JSON_VALUE})
   public @ResponseBody
   List<Map<String, Object>> createForList(@PathVariable String classname, @RequestBody String payloadJson) throws IOException {
      JsonNode jsonNode = om.readTree(payloadJson);

      // logger.debug("create() with body {} of type {}", json,
      // json.getDomainEntityClass());

      Class aClass = getDomainEntityClass(classname);
      List<Map<String, Object>> hashMaps = new ArrayList<>();
      if (jsonNode.isArray()) {
         for (JsonNode json : jsonNode) {
            Object obj = getValue(json.toString(), aClass);
            try {
               Object created = this.dao.save(obj);
               elasticSearchAssistant.syncWithElasticServer(created, classname, false, true);
               Map<String, Object> m = new HashMap<>();
               m.put("success", true);
               m.put("entity", obj);
               m.put("id", ((BaseEntity) created).getId());
               hashMaps.add(m);
            } catch (JsonProcessingException e) {
               logger.error("Error in generic master creation in elastic" + e.getMessage());
            } catch (Exception e) {
               throw new ConflictException("Code or Description already exist",e.getCause());
            }

         }
      }
      return hashMaps;
   }

   @RequestMapping (value = {"/{classname}/", "/{classname}"}, method = RequestMethod.POST, consumes = {
         MediaType.TEXT_PLAIN_VALUE, MediaType.APPLICATION_JSON_VALUE})
   public @ResponseBody
   Map<String, Object> create(@PathVariable String classname, @RequestBody String json) {
      // logger.debug("create() with body {} of type {}", json,
      // json.getDomainEntityClass());

      Class aClass = getDomainEntityClass(classname);

      Object obj = getValue(json, aClass);

      Object created = this.dao.save(obj);
      // Object created1=dao.save(classname);

      try {
         elasticSearchAssistant.syncWithElasticServer(created, classname, false, true);
      } catch (JsonProcessingException e) {
         logger.error("Error in generic master creation in elastic" + e.getMessage());
      }

      Map<String, Object> m = new HashMap<>();
      m.put("success", true);
      m.put("entity", obj);
      m.put("id", ((BaseEntity) created).getId());
      return m;
   }


   /*Predikly Side*/
   @RequestMapping (value = {"/getItemList/", "/GetItemList/"}, method = RequestMethod.POST, consumes = {
         MediaType.TEXT_PLAIN_VALUE, MediaType.APPLICATION_JSON_VALUE})
   public List<Object> getItemList(@RequestBody ItemsRequest itemsRequest) {
      return dao.findByOneQuery(itemsRequest);
   }

   @RequestMapping (value = {"/getCssdItemList/", "/GetCssdItemList/"}, method = RequestMethod.POST, consumes = {
         MediaType.TEXT_PLAIN_VALUE, MediaType.APPLICATION_JSON_VALUE})
   public List<Object> getCssdItemList(@RequestBody ItemsRequest itemsRequest) {
      return dao.findByOneQueryForCssd(itemsRequest);
   }

   @RequestMapping (value = {"/getItemListByParameters/", "/GetItemListByParameters/"}, method = RequestMethod.POST, consumes = {
         MediaType.TEXT_PLAIN_VALUE, MediaType.APPLICATION_JSON_VALUE})
   public List<Object> getItemListByParameters(@RequestBody ItemsParameterRequest itemsRequest) {
      return dao.findItemListByParameterQuery(itemsRequest);
   }

   @RequestMapping (value = {"/{classname}/inquery"}, method = RequestMethod.POST, consumes = {
         MediaType.TEXT_PLAIN_VALUE, MediaType.APPLICATION_JSON_VALUE})
   public List<Object> getObjectByInQuery(@PathVariable String classname, @RequestBody MultipleIdRequest multipleIdRequest) {

      return dao.findByInQuery(getDomainEntityClass(classname), multipleIdRequest);
   }

   @RequestMapping (value = {"/{classname}/inkeyquery"}, method = RequestMethod.POST, consumes = {
         MediaType.TEXT_PLAIN_VALUE, MediaType.APPLICATION_JSON_VALUE})
   public List<Object> getObjectByInKeyQuery(@PathVariable String classname, @RequestBody String json) {

      return dao.findByInKeyQuery(getDomainEntityClass(classname), json);
   }

   @RequestMapping (value = {"/getStoreList/", "/GetStoreList/"}, method = RequestMethod.POST, consumes = {
         MediaType.TEXT_PLAIN_VALUE, MediaType.APPLICATION_JSON_VALUE})
   public List<Object> getItemList(@RequestBody StoresRequest storesRequest) {
      return dao.findByStoreQuery(storesRequest);
   }

   @RequestMapping (value = {"/getItemUomList/", "/GetItemUomList/"}, method = RequestMethod.POST, consumes = {
         MediaType.TEXT_PLAIN_VALUE, MediaType.APPLICATION_JSON_VALUE})
   public List<Object> getItemUomList(@RequestBody ItemUomRequest itemUomRequest) {
      return dao.findByItemUomQuery(itemUomRequest);
   }

   @RequestMapping (value = {"/getStockTakeItemList/", "/GetStockTakeItemList/"}, method = RequestMethod.POST, consumes = {
         MediaType.TEXT_PLAIN_VALUE, MediaType.APPLICATION_JSON_VALUE})
   public List<Object> getStockTakeItemList(@RequestBody StockTakeItemRequest stockTakeItemRequest) {
      return dao.findByStockTakeItemListQuery(stockTakeItemRequest);
   }

	/*@RequestMapping(value = "/{classname}/{id}", method = RequestMethod.PUT, consumes = { MediaType.TEXT_PLAIN_VALUE,
			MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody Map<String, Object> update(@PathVariable String classname, @PathVariable Long id,
			@RequestBody String json) {
		logger.debug("update() of id#{} with body {}", id, json);
		logger.debug("T json is of type {}", json.getClass());
		Class aClass = getDomainEntityClass(classname);
		Object entity = this.dao.findOne(aClass, id);
		Object entity2 = getValue(json, aClass);
		try {
			BeanUtils.copyProperties(entity2, entity);

		} catch (Exception e) {
			logger.warn("while copying properties", e);
			throw e;
		}

		logger.debug("merged entity: {}", entity);

		Object updated = this.dao.update(entity);
		logger.debug("updated enitity: {}", updated);

		Map<String, Object> m = new HashMap();
		m.put("success", true);
		m.put("id", id);
		m.put("updated", updated);
		return m;
	}*/


   /**
    * @param classname
    * @param id
    * @param json
    *
    * @return
    */
  /* @PutMapping (value = "/{classname}/{id}")
   public ResponseEntity update(@PathVariable String classname, @PathVariable Long id, @RequestBody String json) {
      Class aClass = getDomainEntityClass(classname);
      Object object = dao.findOne(getDomainEntityClass(classname), id);
      if (object == null) {
         return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
      } *//*else {
			Object object1 = dao.update(object);
			Object entity2 = getValue(json, aClass);
			try {
				BeanUtils.copyProperties(entity2, object1);
			} catch (Exception e) {
				throw  new ConflictException("Code Or Description Already Present");
			}
			Object updated = this.dao.save(object1);
			try {
				elasticSearchAssistant.syncWithElasticServer(updated,classname,true,true);
			} catch (JsonProcessingException e) {
				logger.error("Error in generic master creation in elastic"+e.getMessage());
			}
			ResponseEntity<Object> objectResponseEntity = ResponseEntity.ok(updated);
			return objectResponseEntity;
		}*//* else {
         try {
            Object object1;
           *//* try {
               object1 = dao.update(object);
            } catch (Exception e) {
               throw new ConflictException("Code or Description Already Present");
            }*//*
            Object entity2 = getValue(json, aClass);
            BeanUtils.copyProperties(entity2, object1);
            Object updated = this.dao.save(object1);
            try {
               elasticSearchAssistant.syncWithElasticServer(updated, classname, true, true);
            } catch (JsonProcessingException e) {
               logger.error("Error in generic master creation in elastic" + e.getMessage());
            }
            ResponseEntity<Object> objectResponseEntity = ResponseEntity.ok(updated);
            return objectResponseEntity;
         } catch (Exception e) {
            throw new ConflictException("Code or Description Already Present");
         }
      }


   }*/

   @PutMapping (value = "/{classname}/{id}")
   public ResponseEntity update(@PathVariable String classname, @PathVariable Long id, @RequestBody String json) {
      Class aClass = getDomainEntityClass(classname);
      Object object = dao.findOne(getDomainEntityClass(classname), id);
      if (object == null) {
         return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
      } else {
         Object object1 = dao.update(object);
         Object entity2 = getValue(json, aClass);
         try {
            BeanUtils.copyProperties(entity2, object1);
         } catch (Exception e) {
            throw  new ConflictException("Code Or Description Already Present");
         }
         Object updated = this.dao.save(object);
         ResponseEntity<Object> objectResponseEntity = ResponseEntity.ok(updated);
         return objectResponseEntity;
      }

   }

  /* @PutMapping(value = "/{classname}/{id}")
   public ResponseEntity update(@PathVariable String classname, @PathVariable Long id, @RequestBody String json) {
      Class aClass = getDomainEntityClass(classname);
      Object object = dao.findOne(getDomainEntityClass(classname), id);
      if (object == null) {
         return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
      } else {
         Object object1 = dao.update(object);
         Object entity2 = getValue(json, aClass);
         try {
            BeanUtils.copyProperties(entity2, object1);
         } catch (Exception e) {
            throw e;
         }
         Object updated = this.dao.save(object1);
         ResponseEntity<Object> objectResponseEntity = ResponseEntity.ok(updated);
         return objectResponseEntity;
      }

   }   */

   @RequestMapping (value = "/{classname}/{id}", method = RequestMethod.DELETE)
   public @ResponseBody
   Map<String, Object> delete(@PathVariable String classname, @PathVariable Serializable id) {
      Class aClass = getDomainEntityClass(classname);
      this.dao.deleteById(id, aClass);
      Map<String, Object> m = new HashMap<>();
      m.put("success", true);
      return m;
   }

   @RequestMapping (path = {"/getmasterconfig/{classname}", "/getMasterConfig/{classname}/"})
   public ResponseEntity GetMasterList(@PathVariable String classname) {
      BaseMasterJsonStructure obj = new BaseMasterJsonStructure();

      List<Object> UIMenuConfig = dao.findByQuery(getDomainEntityClass("UIConfig"), "filtername",
            "'" + classname + "'");

      List list = dao.findAll(getDomainEntityClass(classname));
      obj.setRowList(list);
      if (UIMenuConfig != null && UIMenuConfig.size() > 0) {
         obj.setMasterConfiguration(UIMenuConfig.get(0));
      }
      ResponseEntity listResponse = ResponseEntity.ok(obj);
      return listResponse;
   }

   protected Object getEntity(String payloadJson, Class cls) {
      return getValue(payloadJson, cls);
   }

   protected static <T> T getValue(String payloadJson, Class<T> busClass) throws NullPointerException {
      T ob = null;
      try { // read JSON like DOM Parser

         JsonNode rootNode = om.readTree(payloadJson);
         JsonNode entityNode = rootNode.path("entity");
         if (entityNode != null && entityNode.isMissingNode()) {

            ob = om.readValue(rootNode.toString(), busClass);

         } else {
            ob = om.readValue(entityNode.toString(), busClass);

         }
         return ob;

      } catch (IOException ie) {
         throw new DataException("JSON Parsing Error  " + payloadJson, ie);
      }

   }

   static HashMap<String, Class> hashMap = new HashMap<>();

   private Class getDomainEntityClass(String className) {
      if (this.classes == null || this.classes.isEmpty()) {
         try {
            this.classes = ClassFinder.getClassesMappedByNames("com.sdgt.medcare.master.entity");
         } catch (IOException e) {
            e.printStackTrace();
         } catch (ClassNotFoundException e) {
            e.printStackTrace();
         }
      }
      Class packageDomainEntityClass = null;
      if (classes != null && !classes.isEmpty()) {
         packageDomainEntityClass = classes.get(className);
      }
      if (packageDomainEntityClass == null) {
         packageDomainEntityClass = getPackageDomainEntityClass("com.sdgt.medcare.master.entity.", className);
         if (packageDomainEntityClass == null) {
            packageDomainEntityClass = getPackageDomainEntityClass("com.sdgt.medcare.master.entity.org.", className);

         }
         if (packageDomainEntityClass == null) {
            packageDomainEntityClass = getPackageDomainEntityClass("com.sdgt.medcare.master.entity.global.", className);
         }
         if (packageDomainEntityClass == null) {
            packageDomainEntityClass = getPackageDomainEntityClass("com.sdgt.medcare.master.entity.unit.", className);
         }
         if (packageDomainEntityClass == null) {
            packageDomainEntityClass = getPackageDomainEntityClass("com.sdgt.medcare.master.entity.lab.", className);
         }
      }
      return packageDomainEntityClass;
   }

   private Class getPackageDomainEntityClass(String packageName, String className) {
      // put this classes into hashmap <classname,classitselfe>
      // find by class name

      String path = packageName + className;
      Class hf = hashMap.get(path);
      if (hf != null) {
         return hf;
      }
      try {
         Class<?> aClass = Class.forName(path);
         if (aClass != null) {
            hashMap.put(path, aClass);
         }
         return aClass;
      } catch (ClassNotFoundException e) {
         // throw new RuntimeException(e);
      }
      return null;
   }

   private Class getDomainOrgEntityClass(String className) {
      String path = "com.sdgt.medical.master.entity.organization." + className;
      try {
         return Class.forName(path);
      } catch (ClassNotFoundException e) {
         throw new RuntimeException(e);
      }
   }

   @GetMapping (path = {"/{classname}/properties", "/{classname}/Properties"})
   public List<ClassProperty> getClassProperties(@PathVariable String classname) {
      List<ClassProperty> classProperties = new ArrayList<>();
      classProperties.addAll(getBaseClassProperties());
      Class aClass = getDomainEntityClass(classname);
      for (Field field : aClass.getDeclaredFields()) {
         if (!field.isAnnotationPresent(OneToMany.class)) {
            classProperties.add(createClassPropertyFromField(field));
         }
      }
      classProperties.sort(new Comparator<ClassProperty>() {
         @Override
         public int compare(ClassProperty o1, ClassProperty o2) {
            if (o1.getSequence() != null && o2.getSequence() != null) {
               return o1.getSequence() - o2.getSequence();
            } else if (o1.getSequence() != null && o2.getSequence() == null) {
               return 1;
            } else if (o1.getSequence() == null && o2.getSequence() != null) {
               return -1;
            } else {
               return 1;
            }
         }
      });
      return classProperties;
   }

   @GetMapping (path = {"/ClassByPackage", "/classByPackage"})
   public Map<String, List<String>> getClasses() throws IOException, ClassNotFoundException {
      String basePackage = "com.sdgt.medcare.master.entity";
      if (this.classPackageMap == null || this.classPackageMap.isEmpty()) {
         this.classPackageMap = ClassFinder.getPackageClassNameMapping("com.sdgt.medcare.master.entity");
         classPackageMap.remove(basePackage.split(Pattern.quote("."))[basePackage.split(Pattern.quote(".")).length - 1]);
      }

      return this.classPackageMap;
   }

   @GetMapping (path = {"/Class", "/class"})
   public Map<String, List<String>> getAllClasses() throws IOException, ClassNotFoundException {
      if (this.classPackageMapBase == null || this.classPackageMapBase.isEmpty()) {
         this.classPackageMapBase.put("entity", ClassFinder.getPackageClassNameMapping("com.sdgt.medcare.master.entity").get("entity"));
      }
      return this.classPackageMapBase;
   }

   private List<ClassProperty> getBaseClassProperties() {
      if (this.baseClassProperties == null || this.baseClassProperties.isEmpty()) {
         List<ClassProperty> baseClassProperties1 = new ArrayList<>();
         for (Field field : BaseMaster.class.getDeclaredFields()) {
            baseClassProperties1.add(createClassPropertyFromField(field));
         }
         for (Field field : AbstractBaseEntity.class.getDeclaredFields()) {
            baseClassProperties1.add(createClassPropertyFromField(field));
         }
         this.baseClassProperties = baseClassProperties1;
      }
      return this.baseClassProperties;
   }

   private ClassProperty createClassPropertyFromField(Field field) {
      ClassProperty classProperty = new ClassProperty();
      String name = field.getName();
      String type = field.getType().getSimpleName();
      classProperty.setName(name);
      classProperty.setDisplayName(field.getName());
      if (type.equalsIgnoreCase("String") || type.equalsIgnoreCase("Long")
            || type.equalsIgnoreCase("Integer") || type.equalsIgnoreCase("Boolean")
            || type.equalsIgnoreCase("Date") || type.equalsIgnoreCase("Float")) {
         classProperty.setType(type);
      } else {
         classProperty.setType("Reference");
         classProperty.setClassName(type);
      }
      if (field.isAnnotationPresent(NotNull.class)) {
         classProperty.setNullable(false);
      }
      if (field.isAnnotationPresent(MastersFieldCustomAnnotation.class)) {
         MastersFieldCustomAnnotation annotation = field.getAnnotation(MastersFieldCustomAnnotation.class);
         if (annotation.displayName() != null && !annotation.displayName().isEmpty()) {
            classProperty.setDisplayName(annotation.displayName());
         }
         classProperty.setSequence(annotation.sequence());
         classProperty.setValidationRagex(annotation.velidationRegex());
         classProperty.setEditable(annotation.editableByUser());
         classProperty.setShowToUser(annotation.visibleToUser());
         if (classProperty.getNullable()) {
            classProperty.setNullable(annotation.nullable());
         }
      }
      return classProperty;
   }


}
