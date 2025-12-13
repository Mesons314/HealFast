import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:get/get_core/src/get_main.dart';
import 'package:healfast01/API/ClinicAPI.dart';
import 'package:healfast01/BottomNav/BottomNavigation.dart';
import 'package:healfast01/Controller/clinic_controller.dart';
import 'package:healfast01/Models/clinicModels.dart';
import 'package:healfast01/Routes/RoutesName.dart';
import 'package:healfast01/ScreenPages/User/home_page.dart';
import 'package:healfast01/utils/text_editing_controller.dart';
import 'package:healfast01/widgets/text_field.dart';
import 'home_page.dart';

class DoctorsRegisterationPage extends StatelessWidget{

  final ClinicController clinicController = Get.put(ClinicController());

  bool passwordVisible1 = true;
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.grey,
      appBar: AppBar(
        title: const Text('Doctor Registeration'),
      ),
      body: SingleChildScrollView(
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Container(
              margin: const EdgeInsets.only(top: 10,bottom: 10),
              child: const Center(
                  child: Text('Personal Info', textAlign: TextAlign.center,style: TextStyle(fontSize: 20,fontWeight: FontWeight.w700),)),
            ),
        
            Padding(
              padding: const EdgeInsets.only(left: 10,right: 10,bottom: 10),
              child: SizedBox(
                  width: 400,
                  child: CustomTextField(
                      hintText: 'UserName',
                      textEditingController: clinicController.controller.username,
                      textInputType: TextInputType.text
                  ),
              ),
            ),
            Row(
              children: [
                Expanded(
                  child: Obx(() =>
                      Padding(
                        padding: const EdgeInsets.only(left: 10, right: 5),
                        child: DropdownButtonFormField<String>(
                          decoration: const InputDecoration(
                            labelText: "Gender",
                            border: OutlineInputBorder(),
                          ),
                          value: clinicController.gender.value.isEmpty
                              ? null
                              : clinicController.gender.value,
                          items: const [
                            DropdownMenuItem(value: 'Male', child: Text('Male')),
                            DropdownMenuItem(value: 'Female', child: Text('Female')),
                            DropdownMenuItem(value: 'Other', child: Text('Other')),
                          ],
                          onChanged: (value) {
                            clinicController.gender.value = value!;
                          },
                        ),
                      ),
                  ),
                ),
                const SizedBox(width: 10),
                Padding(
                  padding: const EdgeInsets.only(right : 8),
                  child: SizedBox(
                    width: 200,
                    child: TextField(
                      onTap: () async {
                        DateTime now = DateTime.now();
                        DateTime lastDate = DateTime(2025, 12, 31);

                        DateTime? picked = await showDatePicker(
                          context: context,
                          initialDate: now.isAfter(lastDate) ? lastDate : now,
                          firstDate: DateTime(1900),
                          lastDate: lastDate,
                        );

                        if (picked != null) {
                          clinicController.controller.dob.text =
                          "${picked.day}/${picked.month}/${picked.year}";
                        }
                      },
                      controller: clinicController.controller.dob,
                      decoration: InputDecoration(
                        hintText: "DOB",
                        border: OutlineInputBorder(
                          borderRadius: BorderRadius.circular(12),
                          borderSide: const BorderSide(color: Colors.lightBlueAccent),
                        ),
                      ),
                      readOnly: true,
                    ),
                  ),
                ),
              ],
            ),

            const SizedBox(
              height: 10,
            ),
            Padding(padding: const EdgeInsets.only(left: 10,right: 10),
              child: CustomTextField(
                  hintText: 'Phone No',
                  textEditingController: clinicController.controller.phoneNo,
                  textInputType: TextInputType.phone
              ),
            ),
        
            const SizedBox(
              height: 10,
            ),

            Padding(padding: const EdgeInsets.only(left: 11,right: 11),
            child: CustomTextField(
              isPassword: true,
              showSuffixIcon: true,
              hintText: 'Password',
              textEditingController: clinicController.controller.password,
              textInputType: TextInputType.text,
            )

            ),
            Container(
              margin: const EdgeInsets.only(top: 15,bottom: 12),
              child: const Divider(
                thickness: 2,
                color: Colors.black,
                indent: 10,
                endIndent: 10,
              ),
            ),
            Center(
                  child: Container(
                    margin: const EdgeInsets.only(bottom: 10),
                      child: const Text(
                        'Clinic Info', style: TextStyle(fontSize: 20, fontWeight: FontWeight.w700),textAlign: TextAlign.center,)
                  ),
            ),
        
            Padding(
                padding: const EdgeInsets.only(left: 10,right: 10),
              child: CustomTextField(
                  hintText:"Name of the Clinic",
                  textEditingController: clinicController.controller.clinicName,
              ),
            ),
        
            const SizedBox(
              height: 10,
            ),

        Padding(padding: const EdgeInsets.only(right: 11,left: 11),
        child: CustomTextField(
            hintText: 'First Name',
            textEditingController: clinicController.controller.firstName
        ),
        ),

        const SizedBox(
          height: 10,
        ),

        Padding(padding: const EdgeInsets.only(left: 11,right: 11),
        child: CustomTextField(
          hintText: 'Last Name',
          textEditingController: clinicController.controller.lastName
                ),
        ),
        const SizedBox(
          height: 10,
        ),
        
        Padding(padding: const EdgeInsets.only(left: 11,right: 11),
        child: CustomTextField(
            hintText: 'Degree',
            textEditingController: clinicController.controller.degree
        ),
        ),

        const SizedBox(
          height: 10,
        ),
        Padding(padding: const EdgeInsets.only(left: 11,right: 11),
        child: CustomTextField(
            hintText: 'Speciality',
            textEditingController: clinicController.controller.speciality
        ),
        ),
        const SizedBox(
          height: 10,
        ),
            Padding(
              padding: const EdgeInsets.only(left: 10,right: 10),
              child: CustomTextField(
                  MaxLines: null,
                  hintText: 'Address',
                  textEditingController: clinicController.controller.address,
                  textInputType: TextInputType.text
              ),
            ),

            const SizedBox(
              height: 10,
            ),
            
            Padding(
              padding: const EdgeInsets.only(left: 10,right: 10),
              child: CustomTextField(
                  hintText: 'Clinic Phone No',
                  textEditingController: clinicController.controller.clinicPhoneNo,
                  textInputType: TextInputType.phone
              ),
            ),
            const SizedBox(
              height: 10,
            ),
            Padding(
                padding: const EdgeInsets.only(left: 10,right: 10),
                child: CustomTextField(
                    hintText: 'CLINIC',
                    textEditingController: clinicController.controller.role,
                    textInputType: TextInputType.text
                )
            ),
            const SizedBox(
              height: 30,
            ),
            Center(
              child: SizedBox(
                  height: 50,
                  width: 120,
                  child: ElevatedButton(

                    style: ElevatedButton.styleFrom(
                      elevation: 10,
                      alignment: Alignment.center, // Center the content
                    ),
                    onPressed: () async{
                      print(clinicController.controller.username.text);
                      clinicController.registerDoctor();
                    },
                    child: const Text(
                      'Submit',
                      style: TextStyle(fontSize: 20),
                      textAlign: TextAlign.center, // Center text within the button
                    ),
                  )
              ),
            ),
            const SizedBox(
              height: 20,
            )
          ],
        ),
      ),
    );
  }
}