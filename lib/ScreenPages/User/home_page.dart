import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:healfast01/Controller/user_controller.dart';
import 'package:healfast01/Models/doctor_types.dart';
import 'package:healfast01/Routes/RoutesName.dart';
import 'package:healfast01/ScreenPages/User/clinic_list.dart';
import 'package:healfast01/ScreenPages/User/google_map.dart';
import 'package:healfast01/ScreenPages/User/profile_screen.dart';
import 'package:healfast01/utils/size_config.dart';
import 'package:healfast01/widgets/common_text.dart';
import 'package:healfast01/widgets/local_assets.dart';

import '../../Mock Data/mock_clinic.dart';


class HomePage extends StatefulWidget{
  const HomePage({super.key});

  @override
  State<HomePage> createState() {
    return homepage();
  }
}

class homepage extends State<HomePage>{

  int myIndex = 0;
  final userController = Get.find<UserController>();
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
                    Navigator.push(context, MaterialPageRoute(builder: (context)=>GoogleMaps()));
                  },
                  title: const Text('Home'),
                  leading: const Icon(Icons.home),
                ),
                ListTile(
                  onTap: (){
                    Get.toNamed('/clinicHome');
                  },
                  title: const Text('Clinics'),
                  leading: const Icon(Icons.local_hospital),
                ),
                ListTile(
                  onTap: (){
                    Get.toNamed('/myAppointments');
                  },
                  title: const Text('Past Records'),
                  leading: const Icon(Icons.emergency_recording_sharp),
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
      body: Column(
        children: [
          Align(
            alignment: Alignment.centerLeft,
            child: Padding(
              padding: const EdgeInsets.all(8.0),
              child: CommonText(
                //Here it will be userController.userById.name
                text: 'Hello ${mockClinics[0].firstName}',
                color: Colors.black,
                fontWeight: FontWeight.w700,
                fontSize: 20,
              ),
            ),
          ),

          const Align(
            alignment: Alignment.centerLeft,
              child: Padding(
                padding: EdgeInsets.only(left: 15),
                child: CommonText(text: 'Doctor Types'),
              )),

          Padding(
            padding: const EdgeInsets.only(left: 11,right: 11),
            child: SizedBox(
              height: 80,
              child: ListView.builder(
                scrollDirection: Axis.horizontal,
                itemCount: doctorTypes.length,
                  itemBuilder: (context,index){
                  final doctor = doctorTypes[index];
                    return ClipRRect(
                      borderRadius: BorderRadius.circular(12),
                      child: Container(
                        width: 100,
                        margin: const EdgeInsets.all(4),
                        decoration: BoxDecoration(
                          gradient: const LinearGradient(
                              colors:[Colors.red, Colors.blue],
                            begin: Alignment.centerLeft,
                            end: Alignment.centerRight
                          ),

                          borderRadius: BorderRadius.circular(12)
                        ),
                        child: Center(
                          child: CommonText(text: doctor.name),
                        ),
                      ),
                    );
                  }),
            ),
          ),
          SizedBox(height: SizeConfig.size20,),
          const Align(
            alignment: Alignment.centerLeft,
            child: Padding(
              padding: EdgeInsets.only(left: 15),
              child: CommonText(text: 'Popular Clinics'),
            ),
          ),
          SizedBox(height: SizeConfig.size5,),
          Padding(
            padding: const EdgeInsets.only(left: 11,right: 11),
            child: SizedBox(
              height: 220,
              child: ListView.builder(
                  scrollDirection: Axis.horizontal,
                  itemCount: mockClinics.length,
                  // itemCount: userController.clinicList.length,
                  itemBuilder: (context,index){
                    final clinicData = mockClinics[index];
                    // final clinicData = userController.clinicList[index];
                    return SizedBox(
                      width: 200,
                      child: Column(
                        crossAxisAlignment: CrossAxisAlignment.start,
                        children: [
                          ClipRRect(
                            borderRadius: BorderRadius.circular(12),
                            child: LocalAssets(
                                  imagePath: 'assets/images/img.png',
                                boxFit: BoxFit.cover,
                                height: 120,
                              ),
                          ),
                          const SizedBox(height: 10,),
                          const Row(
                            children: [
                              Icon(
                                  Icons.star,
                                color: Colors.orange,
                              ),
                              CommonText(text: "4.8")
                            ],
                          ),
                          CommonText(
                              text: '${clinicData.firstName!} ${clinicData.lastName}',
                            fontSize: 16,
                            fontWeight: FontWeight.w700,
                          ),
                          CommonText(
                            text: clinicData.speciality!,
                            fontWeight: FontWeight.w400,
                            fontSize: 12,
                          ),
                          CommonText(
                              text: clinicData.clinicName!
                          ),
                        ],
                      ),
                    );
                  }),
            ),
          ),
          SizedBox(height: SizeConfig.size20,),
          const Align(
            alignment: Alignment.centerLeft,
            child: Padding(
              padding: EdgeInsets.only(left: 15),
              child: CommonText(text: 'Past Appointments'),
            ),
          ),
          SizedBox(height: SizeConfig.size5,),
          Padding(
            padding: const EdgeInsets.only(left: 11,right: 11),
            child: SizedBox(
              height: 120,
              child: ListView.builder(
                  scrollDirection: Axis.horizontal,
                  itemBuilder: (context,index){
                    return Container(
                      decoration: BoxDecoration(
                        border: Border.all(color: Colors.blue)
                      ),
                      width: 180,
                      margin: const EdgeInsets.all(4),
                      child: const Padding(
                        padding: EdgeInsets.all(8.0),
                        child: Column(
                          crossAxisAlignment: CrossAxisAlignment.start,
                          children: [
                            CommonText(text: 'Abhinav Dwivedi'),
                            CommonText(text: 'Yash Clinic'),
                            CommonText(text: '22nd September 2025'),
                            CommonText(text: '10:11 AM')
                          ],
                        ),
                      ),
                    );
                  }),
            ),
          ),
          const SizedBox(height: 10,),
          
          const SizedBox(
            height: 80,
            width: 240,
            child: Center(
              child: CommonText(
                  text: 'Keep Taking Care of your health',
                textAlign: TextAlign.center,
                fontSize: 20,
                fontWeight: FontWeight.w700,
              ),
            ),
          )
        ],
      ),
    );
  }

}
