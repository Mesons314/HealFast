
import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:get/get_core/src/get_main.dart';
import 'package:healfast01/Controller/login_controller.dart';
import 'package:healfast01/utils/size_config.dart';

class CustomTextField extends StatelessWidget{
  String? hintText;
  TextEditingController? textEditingController;
  TextInputType? textInputType;
  final MaxLines;
  bool showSuffixIcon;
  bool isPassword;
  final ValueChanged<String>? onChanged;
  CustomTextField({super.key,
    this.hintText,
    this.textEditingController,
    this.textInputType,
    this.MaxLines,
    this.onChanged,
    this.isPassword = false,
    this.showSuffixIcon = false});


  @override
  Widget build(BuildContext context) {
    LoginController loginController = Get.put(LoginController());
    if(isPassword){
    return Obx(()=> TextField(
          keyboardType: textInputType,
          controller: textEditingController,
          obscureText: isPassword ? loginController.passwordVisible.value : false,
          maxLines: isPassword? 1: MaxLines,
      onChanged: onChanged,
          decoration: InputDecoration(
              suffixIcon: showSuffixIcon?
                  IconButton(
                    icon: Icon(
                      loginController.passwordVisible.value? Icons.visibility: Icons.visibility_off
                    ),
                    onPressed: (){
                      loginController.togglePassword();
                      },
                  ):null,
            hintText: hintText,
            border: OutlineInputBorder(
              borderRadius: BorderRadius.circular(SizeConfig.size8),
              borderSide: const BorderSide(
                color: Colors.grey
              )
            )
          ),
      ),
    );
    }else{
      return TextField(
        keyboardType: textInputType,
        controller: textEditingController,
        obscureText: isPassword ? loginController.passwordVisible.value : false,
        maxLines: isPassword? 1: MaxLines,
        onChanged: onChanged,
        decoration: InputDecoration(
            suffixIcon: showSuffixIcon?
            IconButton(
              icon: Icon(
                  loginController.passwordVisible.value? Icons.visibility: Icons.visibility_off
              ),
              onPressed: (){
                loginController.togglePassword();
              },
            ):null,
            hintText: hintText,
            // isDense: true,
            // contentPadding: EdgeInsets.symmetric(vertical: 12, horizontal: 15),
            border: OutlineInputBorder(
                borderRadius: BorderRadius.circular(SizeConfig.size8),
                borderSide: const BorderSide(
                    color: Colors.grey
                )
            )
        ),
      );
    }
  }
}