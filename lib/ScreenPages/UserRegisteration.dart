import 'package:flutter/material.dart';
import 'package:flutter/rendering.dart';
import 'package:healfast01/API/UserAPI.dart';
import 'package:healfast01/BottomNav/BottomNavigation.dart';
import 'package:healfast01/utils/text_editing_controller.dart';
import 'package:healfast01/widgets/textField/text_field.dart';

import '../Models/userModel.dart';

class UserRegisteration extends StatefulWidget{
  @override
  State<UserRegisteration> createState() {
    return userRegisteration();
  }
}

class userRegisteration extends State<UserRegisteration>{

  final TextEditingController dateController = TextEditingController();

  final controller = textEditingController();
  final repo = UserCall();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('User Registeration'),
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
                  child: Text('Personal Info', textAlign: TextAlign.center,style: TextStyle(fontSize: 20,fontWeight: FontWeight.w700),)),
            ),

            Padding(
                padding: EdgeInsets.only(left: 10,right: 10,bottom: 10),
              child: SizedBox(
                width: 400,
                child: CustomTextField(
                    hintText: 'UserName',
                    textEditingController: controller.username,
                    textInputType: TextInputType.text
                )
              ),
            ),
            Row(
              children: [
                Expanded(
                  child: Padding(
                      padding: const EdgeInsets.only(left: 10),
                      child: DropdownMenu<String>(
                        hintText: 'Gender',
                        initialSelection: controller.gender,
                        onSelected: (value){
                          setState(() {
                            controller.gender = value!;
                          });
                        },
                        requestFocusOnTap: true,
                        dropdownMenuEntries: const <DropdownMenuEntry<String>>[
                          DropdownMenuEntry(value: 'Male', label: 'Male'),
                          DropdownMenuEntry(value: 'Female', label: 'Female'),
                          DropdownMenuEntry(value: 'Other', label: 'Other')
                        ],
                        inputDecorationTheme: InputDecorationTheme(
                            border: OutlineInputBorder(
                                borderRadius: BorderRadius.circular(12)
                            )
                        ),
                      )
                  ),
                ),
               Expanded(
                 child: Container(
                   margin: EdgeInsets.only(left: 15,right: 10),
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
                             setState(() {
                               dateController.text = "${picked.day}/${picked.month}/${picked.year}";
                             });
                           }
                         },
                         controller: dateController,
                         decoration: InputDecoration(
                           hintText: "DOB",
                           border: OutlineInputBorder(
                             borderRadius: BorderRadius.circular(12),
                             borderSide: BorderSide(
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
                    textEditingController: controller.firstName,
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
                  textEditingController: controller.lastName,
                  textInputType: TextInputType.text
              )
            ),
            ),

            const SizedBox(
              height: 10,
            ),

            Padding(padding: EdgeInsets.only(left: 11,right: 11),
            child: Container(
              child: CustomTextField(
                  isPassword: true,
                  showSuffixIcon: true,
                  hintText: 'Password',
                  textEditingController: controller.password,
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
                    MaxLines: null,
                    hintText: 'Address',
                    textEditingController: controller.address,
                    textInputType: TextInputType.text
                )
              ),
            ),
            SizedBox(
              height: 10,
            ),
            Padding(
                padding: EdgeInsets.only(left: 10,right: 10),
              child: CustomTextField(
                  hintText: 'Phone No',
                  textEditingController: controller.phoneNo,
                  textInputType: TextInputType.phone
              )
            ),
            SizedBox(
              height: 10,
            ),
            Padding(
                padding: EdgeInsets.only(left: 10,right: 10),
                child: CustomTextField(
                    hintText: 'USER',
                    textEditingController: controller.role,
                    textInputType: TextInputType.text
                )
            ),
            SizedBox(
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
                    try{
                      print(controller.username.text);
                      print(controller.firstName.text);
                      print(controller.lastName.text);
                      UserModel user = UserModel(
                          userName:controller.username.text,
                          gender: controller.gender,
                          dob: controller.dob.text,
                          password: controller.password.text,
                          firstName:controller.firstName.text,
                          lastName: controller.lastName.text,
                          address: controller.address.text,
                          role: controller.role.text,
                          phoneNo: controller.phoneNo.text
                      );
                      await repo.addUser(user);
                      ScaffoldMessenger.of(context).showSnackBar(SnackBar(content: Text("Added successfully")));
                      Navigator.push(context, MaterialPageRoute(builder: (context)=> BottomNavigation()));
                    }catch(e){
                      print('Error: $e');
                      ScaffoldMessenger.of(context)
                          .showSnackBar(const SnackBar(content: Text('Error in adding the user')));
                    }
                  },
                  child: Text(
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