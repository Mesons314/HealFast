
import 'package:flutter/material.dart';

class Profile extends StatefulWidget{
  const Profile({super.key});

  @override
  State<StatefulWidget> createState() {
    return profilePage();
  }
}

class profilePage extends State<Profile>{
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text("Profile"),
        backgroundColor: Colors.lightBlueAccent,
      ),
      body: SafeArea(
        child: Container(
          color: Colors.white,
          child: Stack(
                children:[
                  Container(
                  height: 200,
                  color: Colors.deepPurpleAccent,
                  ),
                  const Column(
                    children:[
                      Padding(
                        padding: EdgeInsets.only(top: 125,left: 120),
                        child: Column(
                          children: [
                            CircleAvatar(
                              radius: 75,
                              child: Icon(Icons.person,size: 75,),
                            ),
                            SizedBox(height: 5,),
                            Text("Abhinav Dwivedi",
                              style: TextStyle(
                                  fontSize: 18,
                                  fontWeight: FontWeight.bold),
                            ),
                          ],
                        ),
                      ),
                    ]
                  ),
                  const SizedBox(
                    height: 20,
                  ),
          const Positioned(
            top: 320,
            left: 0,
            right: 0,
            child: Column(
                children: [
                  Padding(
                    padding: EdgeInsets.symmetric(horizontal: 20),
                    child: Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        Row(
                          children: [
                            Text('Phone', style: TextStyle(fontWeight: FontWeight.bold,)),
                            SizedBox(width: 180),
                            Text('+5999-771-7171',style: TextStyle(fontWeight: FontWeight.w500,)),
                          ],
                        ),
        
                        SizedBox(height: 15),
                        Row(
                          children: [
                            Text('Mail', style: TextStyle(fontWeight: FontWeight.bold,)),
                            SizedBox(width: 200),
                            Text('rita@gmail.com',style: TextStyle(fontWeight: FontWeight.w500,)),
                          ],
                        ),
                      ],
                    ),
                  ),
        
                  Padding(
                    padding: EdgeInsets.only(top: 15,left: 10,right: 10),
                    child: Divider(
                      thickness: 1,
                    ),
                  ),
        
                  ListTile(
                    leading: Icon(Icons.shield_moon_outlined),
                    title: Text("Dark Mode"),
                    trailing: Icon(Icons.toggle_off_outlined),
                  ),
        
                  Padding(
                    padding: EdgeInsets.only(left: 15,right: 15),
                    child: Divider(
                      thickness: 1,
                    ),
                  ),
        
                  ListTile(
                    leading: Icon(Icons.shield_moon_outlined),
                    title: Text("Profile"),
                    trailing: Icon(Icons.person),
                  )
        
                ]
              ),
          )],
          ),
        ),
      ),
    );
  }
}