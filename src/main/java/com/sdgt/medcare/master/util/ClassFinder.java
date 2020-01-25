package com.sdgt.medcare.master.util;

import com.sdgt.medcare.master.customAnnotations.MastersEntityCustomAnnotation;
import com.sdgt.medcare.master.service.common.OtimsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.util.ClassUtils;
import org.springframework.util.SystemPropertyUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class ClassFinder {
    private static Logger logger = LoggerFactory.getLogger(OtimsService.class);

    private static final char PKG_SEPARATOR = '.';

    private static final char DIR_SEPARATOR = File.separatorChar;

    private static final String CLASS_FILE_SUFFIX = ".class";

    private static final String BAD_PACKAGE_ERROR = "Unable to get resources from path '%s'. Are you sure the package '%s' exists?";

    public static List<String> findClassNames(String scannedPackage) {
        String scannedPath = scannedPackage.replace(PKG_SEPARATOR, DIR_SEPARATOR);
        URL scannedUrl = Thread.currentThread().getContextClassLoader().getResource(scannedPath);
        if (scannedUrl == null) {
            throw new IllegalArgumentException(String.format(BAD_PACKAGE_ERROR, scannedPath, scannedPackage));
        }
        File scannedDir = new File(scannedUrl.getFile());
        List<String> classes = new ArrayList<String>();
        for (File file : scannedDir.listFiles()) {
            classes.addAll(findClassNames(file, scannedPackage));
        }
        return classes;
    }

    public static Map<String,Class<?>> find(String scannedPackage) {
        String scannedPath = scannedPackage.replace(PKG_SEPARATOR, DIR_SEPARATOR);
        URL scannedUrl = Thread.currentThread().getContextClassLoader().getResource(scannedPath);
        if (scannedUrl == null) {
            throw new IllegalArgumentException(String.format(BAD_PACKAGE_ERROR, scannedPath, scannedPackage));
        }
        File scannedDir = new File(scannedUrl.getFile());
        Map<String,Class<?>> classes = new HashMap<>();
        for (File file : scannedDir.listFiles()) {
            classes.putAll(find(file, scannedPackage));
        }
        return classes;
    }

    public static Map<String,Class<?>> find(File file, String scannedPackage) {
        Map<String,Class<?>> classes = new HashMap<>();
        String resource = scannedPackage + PKG_SEPARATOR + file.getName();
        if (file.isDirectory()) {
            for (File child : file.listFiles()) {
                classes.putAll(find(child, resource));
            }
        } else if (resource.endsWith(CLASS_FILE_SUFFIX)) {
            int endIndex = resource.length() - CLASS_FILE_SUFFIX.length();
            String className = resource.substring(0, endIndex);
            try {
                classes.put(Class.forName(className).getSimpleName(),Class.forName(className));
            } catch (ClassNotFoundException ignore) {
            }
        }
        return classes;
    }

    private static List<String> findClassNames(File file, String scannedPackage) {
        List<String> classes = new ArrayList<>();

        String resource = scannedPackage + PKG_SEPARATOR + file.getName();
        if (file.isDirectory()) {
            for (File child : file.listFiles()) {
                classes.addAll(findClassNames(child, resource));
            }
        } else if (resource.endsWith(CLASS_FILE_SUFFIX)) {
            int endIndex = resource.length() - CLASS_FILE_SUFFIX.length();
            String className = resource.substring(0, endIndex);
            try {
                classes.add(Class.forName(className).getSimpleName());
            }
            catch (Exception ignore) {
            }
        }
        return classes;
    }

    public static Map<String, Class<?>> getClassesMappedByNames(String basePackage) throws IOException, ClassNotFoundException {
        Map<String, Class<?>> classesByPackage = new HashMap<>();
        try {
            ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
            MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory(resourcePatternResolver);
            String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + resolveBasePackage(basePackage) + "/" + "**/*.class";
            logger.info(packageSearchPath);
            Resource[] resources = resourcePatternResolver.getResources(packageSearchPath);
            for (Resource resource : resources) {
                MetadataReader metadataReader = metadataReaderFactory.getMetadataReader(resource);
                Class aClass = Class.forName(metadataReader.getClassMetadata().getClassName());
                classesByPackage.put(aClass.getSimpleName(), aClass);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return classesByPackage;
    }

    public static Map<String, List<String>> getPackageClassNameMapping(String basePackage) throws IOException, ClassNotFoundException {
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory(resourcePatternResolver);
        Map<String,List<String>> classesByPackage = new HashMap<>();
        String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + resolveBasePackage(basePackage) + "/" + "**/*.class";
        logger.info(packageSearchPath);
        Resource[] resources = resourcePatternResolver.getResources(packageSearchPath);
        for( Resource resource : resources ) {
            MetadataReader metadataReader = metadataReaderFactory.getMetadataReader(resource);
            Class aClass = Class.forName(metadataReader.getClassMetadata().getClassName());
              if(aClass.isAnnotationPresent(MastersEntityCustomAnnotation.class)) {
                  MastersEntityCustomAnnotation entityCustomAnnotation = (MastersEntityCustomAnnotation) aClass.getAnnotation(MastersEntityCustomAnnotation.class);
                  if(entityCustomAnnotation.showOnFrontEnd()==true) {
                      String packageName = aClass.getPackage().getName().split(Pattern.quote("."))[aClass.getPackage()
                            .getName().split(Pattern.quote(".")).length - 1];
                      if (classesByPackage.containsKey(packageName)) {
                          classesByPackage.get(packageName).add(aClass.getSimpleName());
                      } else {
                          List<String> classes = new ArrayList<>();
                          classes.add(aClass.getSimpleName());
                          classesByPackage.put(packageName, classes);
                      }
                  }
              }
        }
        return classesByPackage;
    }

    private static String resolveBasePackage(String basePackage) {
        return ClassUtils.convertClassNameToResourcePath(SystemPropertyUtils.resolvePlaceholders(basePackage));
    }
}

