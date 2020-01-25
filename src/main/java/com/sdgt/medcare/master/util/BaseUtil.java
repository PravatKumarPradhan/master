package com.sdgt.medcare.master.util;

import com.core.base.util.DateAssistant;
import com.sdgt.medcare.master.entity.org.UnitMaster;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;

/**
 * @author Pravat Kumar Pradhan
 */
public class BaseUtil {

    /**
     *
     * @param collectionType
     * @param <T>
     * @return
     */
    public static  <T> boolean isNullAndEmptyCollection(Collection<T> collectionType){
        return collectionType==null&& collectionType.isEmpty();
    }

    /**
     *
     * @param collection
     * @param <T>
     * @return
     */
    public static <T> boolean isNullOrEmptyCollection(Collection<T> collection){
        return  collection==null|| collection.isEmpty();
    }

    /**
     *
     * @param objectType
     * @param <T>
     * @return
     */

    public static<T> boolean isNullAndEmptyObject(Object  objectType ){
        return  objectType==null&& objectType.getClass().getName().isEmpty();
    }

    /**
     *
     * @param object
     * @return
     */
    public static boolean isNullOrEmptyObject(Object object){
        return object==null || object.getClass().getName().isEmpty();
    }


    /**
     * @param unitIdFromCode
     *
     * @return
     */
    public  static  Long getUnitId(Optional<UnitMaster> unitIdFromCode) {
        Long unitId = 0L;
        if (unitIdFromCode.isPresent()) {
            UnitMaster unit = unitIdFromCode.get();
            unitId = Long.parseLong(unit.getId());
        }
        return unitId;
    }

    public static Date epchocTosqlDate(Long epochDateTime){

        //LocalDateTime dateTime=LocalDateTime.ofInstant(Instant.ofEpochMilli(epochDateTime), ZoneId.systemDefault());
    //    Date  date=new Date(Instant.ofEpochSecond(epochDateTime).);
      Date date1=  DateAssistant.getDate(epochDateTime)  ;
        return date1;

    }


}
