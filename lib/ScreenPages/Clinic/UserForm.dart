import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class UserForm extends StatefulWidget{
  @override
  State<StatefulWidget> createState() {
    return userform();
  }
}

class userform extends State<UserForm>{
  @override
  Widget build(BuildContext context){
    return Scaffold(
      appBar: AppBar(
        title: Text('User Data'),
      ),
      body: SafeArea(
        child: Column(
          children: [
            Container(
              padding: EdgeInsets.only(top: 15),
              height: 60,
              width: double.infinity, // make it full width (optional)
              decoration: BoxDecoration(
                color: Colors.red,
              ),
              child: Text(
                "Your Text Here",
                style: TextStyle(
                  color: Colors.white,
                  fontSize: 18,
                  fontWeight: FontWeight.bold,
                ),
                textAlign: TextAlign.center, // center the text horizontally
              ),
            ),
          ],
        ),
      ),
    );
  }
}