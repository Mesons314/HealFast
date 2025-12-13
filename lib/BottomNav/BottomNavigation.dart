
import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:healfast01/Controller/bottom_navigation_controller.dart';
import 'package:healfast01/ScreenPages/User/clinic_list.dart';
import 'package:healfast01/ScreenPages/User/home_page.dart';
import 'package:healfast01/ScreenPages/User/profile_screen.dart';

class BottomNavigation extends StatelessWidget{
  BottomNavigation({super.key});
  final bottomNavController = Get.find<BottomNavigationController>();
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      bottomNavigationBar: SafeArea(
        child: Padding(
          padding: const EdgeInsets.only(top: 0),
          child: SizedBox(
            height: 60,
            child: Obx(
                  ()=>BottomNavigationBar(
                  currentIndex: bottomNavController.currIndex.value,
                  onTap: bottomNavController.pageChange,
                  type: BottomNavigationBarType.fixed,
                  items: const <BottomNavigationBarItem>[
                    BottomNavigationBarItem(
                      icon: Icon(Icons.home),
                      label: 'Home',
                    ),
                    BottomNavigationBarItem(
                      icon: Icon(Icons.medical_information),
                      label: 'Clinic',
                    ),
                    BottomNavigationBarItem(
                        icon: Icon(Icons.info),
                        label: 'Profile'
                    )
                  ]
              ),
            ),
          ),
        ),
      ),

      body: SafeArea(
        child: PageView(
          controller: bottomNavController.pageController,
          children: [
            HomePage(),
            Clinic(),
            Profile()
          ],
        ),
      )
    );
  }

}