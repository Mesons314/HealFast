
import 'package:flutter/material.dart';
import 'package:healfast01/BottomNav/BottomNavigation.dart';
import 'package:healfast01/Routes/RoutesName.dart';
import 'package:healfast01/ScreenPages/DoctorsRegisterationPage.dart';
import 'package:healfast01/ScreenPages/HomePage.dart';
import 'package:healfast01/ScreenPages/Login.dart';
import 'package:healfast01/ScreenPages/Profile.dart';
import 'package:healfast01/ScreenPages/Splash_Screen.dart';
import 'package:healfast01/ScreenPages/UserRegisteration.dart';

class Routes{

  static Route<dynamic> generateRoute(RouteSettings settings){
    switch(settings.name){
      case RoutesName.userRegister:
        return MaterialPageRoute(builder: (context)=>UserRegisteration());
      case RoutesName.clinicRegister:
        return MaterialPageRoute(builder: (context)=>DoctorsRegisterationPage());
      case RoutesName.clinicName:
        return MaterialPageRoute(builder: (context)=>HomePage());
      case RoutesName.homePage:
        return MaterialPageRoute(builder: (context)=>HomePage());
      case RoutesName.login:
        return MaterialPageRoute(builder: (context)=>Login());
      case RoutesName.profilePage:
        return MaterialPageRoute(builder: (context)=>Profile());
      case RoutesName.splashScreen:
        return MaterialPageRoute(builder: (context)=>SplashScreen());
      case RoutesName.bottomNavigation:
        return MaterialPageRoute(builder: (context)=>BottomNavigation());
      default:
        return MaterialPageRoute(builder: (context){
          return const Scaffold(
            body: Center(child: Text("No page is present")),
          );
        });
    }
  }
}