package com;

import java.util.HashSet;
import java.util.Set;
 
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import service.ConfigurationResource;
 
/*
 * Simple REST webservice using RestEasy
 * demonstrating GET/PUT/POST/DELTE
 */
@ApplicationPath("/network-management")
public class NetworkApplication extends Application {
 
   private Set<Object> singletons = new HashSet<>();
   private Set<Class<?>> empty = new HashSet<>();
 
   public NetworkApplication() {
      singletons.add(new ConfigurationResource());
   }
 
   @Override
   public Set<Class<?>> getClasses() {
      return empty;
   }
 
   @Override
   public Set<Object> getSingletons() {
      return singletons;
   }
}