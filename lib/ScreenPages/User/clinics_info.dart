import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:get/get_core/src/get_main.dart';
import 'package:healfast01/widgets/common_text.dart';
import 'package:healfast01/widgets/custom_button.dart';
import 'package:healfast01/widgets/local_assets.dart';

import '../../Controller/user_controller.dart';
import '../../widgets/InfoROw.dart';

class ClinicsInfo extends StatelessWidget{
  final UserController userController = Get.find<UserController>();

  @override
  Widget build(BuildContext context) {
    final int clinicId = Get.arguments as int;
    final clinicData = userController.getClinicById(clinicId);
    return Scaffold(
      appBar: AppBar(
        title: const Text('Clinics Info'),
      ),
      body: Padding(
        padding: const EdgeInsets.all(8.0),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            LocalAssets(
                imagePath: 'assets/images/img.png',
              height: 200,
              width: double.infinity,
            ),
            const SizedBox(height: 10,),
            Row(
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              children: [

                CommonText(
                  text: '${clinicData.firstName!} ${clinicData.lastName}',
                  fontWeight: FontWeight.w700,
                  fontSize: 18,
                ),
                Row(
                  children: [
                    Icon(
                      Icons.star,
                      color: Colors.yellow,
                    ),
                    CommonText(text: '4.8')
                  ],
                ),
              ],
            ),
            CommonText(
              text: clinicData.speciality!,
              fontWeight: FontWeight.w400,
              fontSize: 14,
            ),
            const SizedBox(height: 10,),
            const Divider(height: 5,),
            SizedBox(height: 10,),
            InfoRow(label: 'Hospital Name', value: clinicData.clinicName!),
            InfoRow(label: 'Graduation Degree', value: clinicData.graduationDegree!),
            InfoRow(label: 'Sitting Time', value: 'Mon–Fri, 10AM–7PM'),
            InfoRow(label: 'Address', value: clinicData.address!),
            InfoRow(label: 'Phone No', value: clinicData.phoneNo!),

            const CommonText(
                text: 'Book an appointment',
              fontSize: 14,
              fontWeight: FontWeight.w700,
            ),
            PositiveCustomBtn(
                onTap: () {
                  Get.dialog(
                    Dialog(
                      child: Padding(
                        padding: const EdgeInsets.all(16.0),
                        child: Column(
                          mainAxisSize: MainAxisSize.min, // Prevents unwanted expansion
                          children: [
                            const CommonText(text: "Do you want to book an appointment"),
                            const SizedBox(height: 16),
                            Row(
                              mainAxisAlignment: MainAxisAlignment.center, // Center the buttons
                              children: [
                                Expanded( // Constrain button
                                  child: PositiveCustomBtn(
                                      onTap: (){
                                        Get.toNamed('/appointmentForm');
                                        },
                                      title: 'YES'),
                                ),
                                SizedBox(width: 10),
                                Expanded( // Constrain button
                                    child: PositiveCustomBtn(onTap: Get.back, title: 'NO')
                                ),
                              ],
                            ),
                          ],
                        ),
                      ),
                    ),
                  );
                },
                title: 'Book'
            )


          ],
        ),
      ),
    );
  }

}
