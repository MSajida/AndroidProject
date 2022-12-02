package com.example.studibook.ui;

public class ProjectsListModel {

 String Food;
  String Travel;
  String TransportTourism;
  String Evironment;
  String ChatMedia;
  String MarketPlaceEcommerce;
  String EventManagementEventTracking;
  String Medical;
  String Miscellaneous;
  String EducationTechnology;
  String Housing;

 public String getHousing() {
  return Housing;
 }

 public void setHousing(String housing) {
  Housing = housing;
 }

 public ProjectsListModel() {
 }

 public String getFood() {
  return Food;
 }

 public void setFood(String food) {
  Food = food;
 }

 public String getTravel() {
  return Travel;
 }

 public void setTravel(String travel) {
  Travel = travel;
 }

 public String getTransportTourism() {
  return TransportTourism;
 }

 public void setTransportTourism(String transportTourism) {
  TransportTourism = transportTourism;
 }

 public String getEvironment() {
  return Evironment;
 }

 public void setEvironment(String evironment) {
  Evironment = evironment;
 }

 public String getChatMedia() {
  return ChatMedia;
 }

 public void setChatMedia(String chatMedia) {
  ChatMedia = chatMedia;
 }

 public String getMarketPlaceEcommerce() {
  return MarketPlaceEcommerce;
 }

 public void setMarketPlaceEcommerce(String marketPlaceEcommerce) {
  MarketPlaceEcommerce = marketPlaceEcommerce;
 }

 public String getEventManagementEventTracking() {
  return EventManagementEventTracking;
 }

 public void setEventManagementEventTracking(String eventManagementEventTracking) {
  EventManagementEventTracking = eventManagementEventTracking;
 }

 public String getMedical() {
  return Medical;
 }

 public void setMedical(String medical) {
  Medical = medical;
 }

 public String getMiscellaneous() {
  return Miscellaneous;
 }

 public void setMiscellaneous(String miscellaneous) {
  Miscellaneous = miscellaneous;
 }

 public String getEducationTechnology() {
  return EducationTechnology;
 }

 public void setEducationTechnology(String educationTechnology) {
  EducationTechnology = educationTechnology;
 }

 public ProjectsListModel(String food, String travel, String transportTourism, String evironment, String chatMedia,
                          String marketPlaceEcommerce, String eventManagementEventTracking, String medical,
                          String miscellaneous, String educationTechnology, String housings) {
  Food = food;
  Travel = travel;
  Housing=housings;
  TransportTourism = transportTourism;
  Evironment = evironment;
  ChatMedia = chatMedia;
  MarketPlaceEcommerce = marketPlaceEcommerce;
  EventManagementEventTracking = eventManagementEventTracking;
  Medical = medical;
  Miscellaneous = miscellaneous;
  EducationTechnology = educationTechnology;
 }
}
