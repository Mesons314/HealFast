
import 'package:flutter/material.dart';

class CustomTextField extends StatefulWidget{
  String? hintText;
  TextEditingController? textEditingController;
  TextInputType? textInputType;
  final MaxLines;
  bool showSuffixIcon;
  bool isPassword;
  CustomTextField({super.key, this.hintText, this.textEditingController,this.textInputType,this.MaxLines, this.isPassword = false,
  this.showSuffixIcon = false});

  @override
  State<StatefulWidget> createState() {
    return custom_textField();
  }
}

class custom_textField extends State<CustomTextField>{

  bool _passwordVisible = false;

  @override
  void initState(){
    super.initState();
    _passwordVisible = widget.isPassword;
  }
  @override
  Widget build(BuildContext context) {
    return TextField(
      keyboardType: widget.textInputType,
      controller: widget.textEditingController,
      obscureText: widget.isPassword ? _passwordVisible : false,
      maxLines: widget.isPassword? 1: widget.MaxLines,
      decoration: InputDecoration(
        suffixIcon: widget.showSuffixIcon? IconButton(onPressed: (){
          setState(() {
            _passwordVisible = !_passwordVisible;
          });
        }, icon: Icon(_passwordVisible? Icons.visibility: Icons.visibility_off)
        ):null,
        hintText: widget.hintText,
        border: OutlineInputBorder(
          borderRadius: BorderRadius.circular(12),
          borderSide: const BorderSide(
            color: Colors.lightBlueAccent
          )
        )
      ),
    );
  }

}