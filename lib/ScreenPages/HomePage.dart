import 'package:flutter/material.dart';
import 'package:healfast01/Routes/RoutesName.dart';
import 'package:healfast01/ScreenPages/Clinic.dart';
import 'package:healfast01/ScreenPages/Profile.dart';


class HomePage extends StatefulWidget{
  const HomePage({super.key});

  @override
  State<HomePage> createState() {
    return homepage();
  }
}

class homepage extends State<HomePage>{

  int myIndex = 0;

  //instead of writing the function inside the onTap we will
  //write it here and then use it in different places
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text("Home Page"),
      ),
      drawer: Drawer(
        child: ListView(
          children: [
            SizedBox(
              height: 200,
              child: DrawerHeader(
                decoration: const BoxDecoration(
                  color: Colors.blue,
                ),
                child: Column(
                  children: [
                    const CircleAvatar(
                      radius: 65,
                    ),
                    const SizedBox(
                      height: 5,
                    ),
                    InkWell(
                        onTap: (){
                          Navigator.pushNamed(context,RoutesName.profilePage);
                        },
                        child: const Text('Profile')
                    )
                  ],
                ),
              ),
            ),
            Column(
              children: [
                ListTile(
                  onTap: (){

                  },
                  title: const Text('Home'),
                  leading: const Icon(Icons.home),
                ),
                ListTile(
                  onTap: (){
                    Navigator.pushNamed(context, RoutesName.clinicName);
                  },
                  title: const Text('Clinics'),
                  leading: const Icon(Icons.local_hospital),
                ),
                const ListTile(
                  title: Text('Past Records'),
                  leading: Icon(Icons.emergency_recording_sharp),
                ),
                const ListTile(
                  title: Text('Help'),
                  leading: Icon(Icons.help),
                ),
                const ListTile(
                  title: Text('Logout'),
                  leading: Icon(Icons.logout),
                ),
              ],
            )
          ],
        ),
      ),
    );
  }

}
