import 'package:flutter/material.dart';
import 'package:healfast01/API/UserAPI.dart';
import 'package:healfast01/BottomNav/BottomNavigation.dart';
import 'package:healfast01/Models/user_login.dart';
import 'package:healfast01/utils/text_editing_controller.dart';

class Login extends StatefulWidget{
  @override
  State<Login> createState() {
   return login();
  }
}

class login extends State<Login>{
  final controller = textEditingController();
  final repo = UserCall();
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("Login"),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Padding(
              padding: EdgeInsets.only(left: 15,right: 15),
              child: TextField(
                controller: controller.username,
                decoration: InputDecoration(
                  hintText: "Username",
                  border: OutlineInputBorder(
                    borderRadius: BorderRadius.circular(22),
                    borderSide: BorderSide(
                      color: Colors.lightBlueAccent
                    )
                  )
                ),
              ),
            ),
            SizedBox(
              height: 10,
            ),
            Padding(
              padding: EdgeInsets.only(left: 15 ,right: 15),
              child: TextField(
                obscureText: true,
                controller: controller.password,
                decoration: InputDecoration(
                  hintText: "Password",
                  border: OutlineInputBorder(
                    borderSide: BorderSide(
                      color: Colors.lightBlueAccent
                    ),
                    borderRadius: BorderRadius.circular(22)
                  )
                ),
              ),
            ),

            SizedBox(
              height: 50,
            ),

            ElevatedButton(onPressed: () async{
              LoginRequest login = LoginRequest(
                userName: controller.username.text,
                password: controller.password.text
              );
              bool correct = await repo.loginUser(login);
              if(correct){
                Navigator.push(context, MaterialPageRoute(builder: (context)=>BottomNavigation()));
              }else{
                ScaffoldMessenger.of(context).showSnackBar(SnackBar(content: Text('Failed to lig in')));
              }

            },
                child: Text(
                    "Submit"
                )
            )
          ],
        ),
      ),
    );
  }

}