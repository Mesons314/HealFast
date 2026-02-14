import 'package:flutter/material.dart';
import 'package:flutter/rendering.dart';
import 'package:get/get.dart';
import 'package:get/get_core/src/get_main.dart';
import 'package:healfast01/Controller/user_controller.dart';
import 'package:healfast01/widgets/text_field.dart';

class UserRegisteration extends StatelessWidget{

  final userController = Get.find<UserController>();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('User Registeration'),
      ),
      backgroundColor: Colors.grey,
      resizeToAvoidBottomInset: true,
      body: SingleChildScrollView(
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Container(
              margin: const EdgeInsets.only(top: 10,bottom: 10),
              child: const Center(
                  child: Text('Personal Info', textAlign: TextAlign.center,
                    style: TextStyle(fontSize: 20,fontWeight: FontWeight.w700),)),
            ),

            Padding(
                padding: const EdgeInsets.only(left: 10,right: 10,bottom: 10),
              child: SizedBox(
                width: 400,
                child: CustomTextField(
                    hintText: 'UserName',
                    textEditingController: userController.controller.username,
                    textInputType: TextInputType.text
                )
              ),
            ),
            Row(
              children: [
                Expanded(
                  child: Obx(()=>
                      Padding(
                          padding: const EdgeInsets.only(left: 10, right: 5),
                        child: DropdownButtonFormField<String>(
                            decoration: const InputDecoration(
                              labelText: "Gender",
                              border: OutlineInputBorder(),
                            ),
                            value: userController.gender.value.isEmpty
                                ?null
                                :userController.gender.value,
                            items:const [
                              DropdownMenuItem(value: 'Male', child: Text('Male')),
                              DropdownMenuItem(value: 'Female', child: Text('Female')),
                              DropdownMenuItem(value: 'Other', child: Text('Other')),
                            ],
                            onChanged: (value){
                              userController.gender.value = value!;
                            })
                    ),
                  ),
                ),
               Expanded(
                 child: Container(
                   margin: const EdgeInsets.only(left: 15,right: 10),
                   width: 200,
                   child: InkWell(
                       child: TextField(
                         onTap: () async{
                           DateTime now = DateTime.now();
                           DateTime lastDate = DateTime(2025,12,31);

                           DateTime? picked = await showDatePicker(
                               context: context,
                               initialDate: now.isAfter(lastDate)?lastDate: now,
                               firstDate: DateTime(1900),
                               lastDate: lastDate
                           );
                           if(picked != null){
                             userController.controller.dob.text =
                             "${picked.day}/${picked.month}/${picked.year}";

                           }
                         },
                         controller: userController.controller.dob,
                         decoration: InputDecoration(
                           hintText: "DOB",
                           border: OutlineInputBorder(
                             borderRadius: BorderRadius.circular(12),
                             borderSide: const BorderSide(
                               color: Colors.lightBlueAccent
                             )
                           )
                         ),
                         readOnly: true,
                       ),
                   ),
                 ),
               )
              ],
            ),

            const SizedBox(
              height: 10,
            ),

            Padding(padding: const EdgeInsets.only(left: 10, right: 10),
              child: Container(
                child: CustomTextField(
                    hintText: 'First Name',
                    textEditingController: userController.controller.firstName,
                    textInputType: TextInputType.text
                )
              ),
            ),

            const SizedBox(
              height: 10,
            ),

            Padding(padding: const EdgeInsets.only(left: 10,right: 10),
            child: Container(
              child: CustomTextField(
                  hintText: 'Last Name',
                  textEditingController: userController.controller.lastName,
                  textInputType: TextInputType.text
              )
            ),
            ),

            const SizedBox(
              height: 10,
            ),

            Padding(
              padding: const EdgeInsets.only(left: 11,right: 11),
              child: CustomTextField(
                isPassword: true,
                showSuffixIcon: true,
                hintText: 'Password',
                textEditingController: userController.controller.password,
                textInputType: TextInputType.text
              ),
            ),

            const SizedBox(
              height: 10,
            ),
            Padding(padding: const EdgeInsets.only(left: 10,right: 10),
              child: Container(
                child: CustomTextField(
                    MaxLines: null,
                    hintText: 'Address',
                    textEditingController: userController.controller.address,
                    textInputType: TextInputType.text
                )
              ),
            ),
            const SizedBox(
              height: 10,
            ),
            Padding(
                padding: const EdgeInsets.only(left: 10,right: 10),
              child: CustomTextField(
                  hintText: 'Phone No',
                  textEditingController: userController.controller.phoneNo,
                  textInputType: TextInputType.phone
              )
            ),
            const SizedBox(
              height: 10,
            ),
            Padding(
                padding: const EdgeInsets.only(left: 10,right: 10),
                child: CustomTextField(
                    hintText: 'USER',
                    textEditingController: userController.controller.role,
                    textInputType: TextInputType.text
                )
            ),
            const SizedBox(
              height: 50,
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
                    userController.registerUser();
                  },
                  child: const Text(
                    'Submit',
                    style: TextStyle(fontSize: 20),
                    textAlign: TextAlign.center, // Center text within the button
                  ),
                )
              ),
            )
          ],
        ),
      ),
    );
  }

}