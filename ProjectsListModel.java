package com.example.studibook.ui;

public class ProjectsListModel {
 String Food;
 String Travel;
 String Environment;
 String Medical;
 String Miscellaneous;

 public ProjectsListModel(String food, String travel, String environment, String medical, String miscellaneous) {
  Food = food;
  Travel = travel;
  Environment = environment;
  Medical = medical;
  Miscellaneous = miscellaneous;
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

 public String getEnvironment() {
  return Environment;
 }

 public void setEnvironment(String environment) {
  Environment = environment;
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
}
