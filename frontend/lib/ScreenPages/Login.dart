import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:get/get_core/src/get_main.dart';
import 'package:healfast01/API/UserAPI.dart';
import 'package:healfast01/Controller/login_controller.dart';
import 'package:healfast01/Models/user_login.dart';
import 'package:healfast01/utils/text_editing_controller.dart';
import 'package:healfast01/widgets/common_text.dart';
import 'package:healfast01/widgets/custom_button.dart';
import 'package:healfast01/widgets/local_assets.dart';
import 'package:healfast01/widgets/text_field.dart';

import '../utils/size_config.dart';

class Login extends StatelessWidget{

  final controller = textEditingController();
  final repo = UserAPI();

  @override
  Widget build(BuildContext context) {
    final loginController = Get.put(LoginController());
    return Scaffold(
      appBar: AppBar(
        title: Text("Login"),
      ),
      body: SingleChildScrollView(
        child: Column(
            children: [
              LocalAssets(
                  imagePath: "assets/images/medical.png",
                height: SizeConfig.size120,
                width: SizeConfig.size120,
              ),
              SizedBox(
                height: SizeConfig.size20,
              ),
              CommonText(
                text: "Welcome Back !",
                fontWeight: FontWeight.w700,
                fontSize: SizeConfig.size20,
              ),
              SizedBox(
                height: SizeConfig.large,
              ),
              CommonText(text: "Use Credentials to access your account!",
                fontSize: SizeConfig.size12,
                color: Colors.grey,
                fontWeight: FontWeight.w500,
              ),
              SizedBox(
                height: SizeConfig.size55,
              ),
              Padding(
                padding: const EdgeInsets.only(left: 15,right: 15),
                child: CustomTextField(
                  hintText: "UserName",
                  textEditingController: loginController.controller.username,
                  // onChanged: (value)=>loginController.username.value,
                )
              ),
              SizedBox(
                height: SizeConfig.size20,
              ),
              Padding(
                padding: const EdgeInsets.only(left: 15 ,right: 15),
                child: CustomTextField(
                  isPassword: true,
                  hintText: "Password",
                  showSuffixIcon: true,
                  textEditingController: loginController.controller.password,
                  // onChanged: (value) => loginController.password.value,
                )
              ),
              SizedBox(
                height: SizeConfig.size12,
              ),
              Padding(
                padding: const EdgeInsets.only(right:15),
                child: Align(
                  alignment: Alignment.centerRight,
                  child: InkWell(
                    child: CommonText(
                        text: "Forgot Password?",
                      color: Colors.lightBlueAccent,
                      fontSize: SizeConfig.size12,
                    ),
                    onTap: (){

                    },
                  ),
                ),
              ),
              const SizedBox(
                height: 50,
              ),

              Padding(
                padding: const EdgeInsets.only(left: 15,right: 15),
                child:
              //   CustomBtn(onTap: ()async{
              //     loginController.loginController();
              //   }, title: "Login",
              //     bgColor: Colors.blue,
              //   ),
              // ),
              ElevatedButton(onPressed: () async{
                loginController.loginController();
              }, child: const Text(
                  "Login"
              )
              )
              )
            ],
          ),
      ),
    );
  }

}