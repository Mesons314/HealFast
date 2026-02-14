import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:healfast01/widgets/common_text.dart';
import 'package:healfast01/widgets/local_assets.dart';

import '../../Controller/user_controller.dart';
import '../../Mock Data/mock_clinic.dart';

class Clinic extends StatelessWidget {

  final UserController userController = Get.find<UserController>();
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("Clinic"),
      ),
      // backgroundColor: Colors.white38,
      body:ListView.builder(
          itemCount: 5,
          itemBuilder: (context, index) {
            // final clinicData = userController.clinicList[index];
            final clinicData = mockClinics[index];
            return InkWell(
              onTap: (){
                userController.toClinic(clinicData.id!);
              },
              child: Padding(
                padding: const EdgeInsets.only(left: 10,right: 10,top: 8),
                child: Material(
                  elevation: 4,
                  borderRadius: BorderRadius.circular(12),
                  child: ClipRRect(
                    borderRadius: BorderRadius.circular(12),
                    child: Container(
                      color: Colors.white, // needed for Material to show shadow
                      child: Padding(
                        padding: EdgeInsets.all(8),
                        child: Row(
                          children: [
                            ClipRRect(
                              borderRadius: BorderRadius.circular(12),
                              child: LocalAssets(
                                imagePath: 'assets/images/img.png',
                                boxFit: BoxFit.cover,
                                height: 100,
                                width: 100,
                              ),
                            ),
                            const SizedBox(width: 10,),
                            Column(
                              crossAxisAlignment: CrossAxisAlignment.start,
                              children: [
                                Row(
                                  mainAxisSize: MainAxisSize.max,
                                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                                  children: [
                                    CommonText(
                                      text: '${clinicData.firstName!} ${clinicData.lastName}',
                                      fontWeight: FontWeight.w700,
                                      fontSize: 18,
                                    ),
                                    const Row(
                                      children: [
                                        Icon(Icons.star, color: Colors.yellow),
                                        SizedBox(width: 4),
                                        CommonText(text: '4.8'),
                                      ],
                                    ),
                                  ],
                                ),
                                CommonText(
                                  text: clinicData.speciality!,
                                  // text: 'Heart Surgeon',
                                  fontWeight: FontWeight.w400,
                                  fontSize: 14,
                                ),
                                const SizedBox(height: 5,),
                                Row(
                                  children: [
                                    const Icon(
                                        Icons.location_on
                                    ),
                                    CommonText(
                                      text: clinicData.address!,
                                        // text: 'Location'
                                    )
                                  ],
                                )
                              ],
                            ),
                          ],
                        ),
                      ),
                    ),
                  ),
                ),
              ),
            );
          },
        ),
    );
  }
}
