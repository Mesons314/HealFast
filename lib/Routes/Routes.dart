import 'package:get/get.dart';
import 'package:flutter/material.dart';
import 'package:healfast01/Routes/RoutesName.dart';
import 'package:healfast01/ScreenPages/Clinic/home_page.dart';
import 'package:healfast01/ScreenPages/Splash_Screen.dart';
import 'package:healfast01/ScreenPages/User/appointment_details.dart';
import 'package:healfast01/ScreenPages/User/clinics_info.dart';
import 'package:healfast01/ScreenPages/User/user_registeration_screen.dart';
import 'package:healfast01/ScreenPages/Login.dart';
import 'package:healfast01/ScreenPages/User/home_page.dart';
import 'package:healfast01/ScreenPages/Clinic/DoctorsRegisterationPage.dart';
import 'package:healfast01/BottomNav/BottomNavigation.dart';
import 'package:healfast01/ScreenPages/User/profile_screen.dart';
import 'package:healfast01/ScreenPages/User/appointment_form.dart';

import '../ScreenPages/User/clinic_list.dart';

class AppRoutes {
  static final pages = [
    GetPage(name: RoutesName.splashScreen, page: () => SplashScreen()),
    GetPage(name: RoutesName.userRegister, page: () => UserRegisteration()),
    GetPage(name: RoutesName.clinicRegister, page: () => DoctorsRegisterationPage()),
    GetPage(name: RoutesName.clinicName, page: () => Clinic()),
    GetPage(name: RoutesName.homePage, page: () => HomePage()),
    GetPage(name: RoutesName.login, page: () => Login()),
    GetPage(name: RoutesName.profilePage, page: () => Profile()),
    GetPage(name: RoutesName.bottomNavigation, page: () => BottomNavigation()),
    GetPage(name: RoutesName.clinicInfo, page: ()=>ClinicsInfo()),
    GetPage(name: RoutesName.appointmentForm, page: ()=>AppointmentForm()),
    GetPage(name: RoutesName.myAppointments, page: ()=>AppointmentDetails())
  ];
}
