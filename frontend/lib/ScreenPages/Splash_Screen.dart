import 'package:flutter/material.dart';
import 'package:healfast01/main.dart';

class SplashScreen extends StatefulWidget{
  const SplashScreen({super.key});

  @override
  State<SplashScreen> createState() {
   return splashScreen();
  }
}

class splashScreen extends State<SplashScreen>{
  
  @override
  void initState(){
    super.initState();
    Future.delayed(const Duration(seconds:4),(){
      Navigator.pushReplacement(context, MaterialPageRoute(builder: (context)=> const MyHomePage(title: 'Heal Fast')
      ));
    });
  }
  
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Container(
        color: Colors.lightBlue,
        child: Center(
          child: Container(

            height: 300,
            width: 300,
            decoration: BoxDecoration(
              color: Colors.green,
              borderRadius: BorderRadius.circular(15),
              boxShadow: [
                BoxShadow(
                  blurRadius: 6
                )
              ]
            ),
            child: Center(
              child: Text('Heal Fast',
              style: TextStyle(color: Colors.white,fontWeight: FontWeight.bold,fontSize: 45),),
            ),
          ),
        ),
      ),
    );
  }
}