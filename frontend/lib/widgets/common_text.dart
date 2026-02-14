import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:healfast01/utils/size_config.dart';

class CommonText extends StatelessWidget{
  final double? fontSize;
  final FontWeight? fontWeight;
  final Color? color;
  final String text;
  final int? maxLines;
  final TextOverflow? overflow;
  final TextAlign? textAlign;
  const CommonText({
   Key? key,
    required this.text,
    this.fontWeight,
    this.maxLines,
    this.color,
    this.fontSize,
    this.overflow,
    this.textAlign
});


  @override
  Widget build(BuildContext context) {
    return Text(
      text,
      maxLines: maxLines,
      textAlign: textAlign,
      style: TextStyle(
        fontSize: fontSize ?? SizeConfig.size12,
        fontWeight: fontWeight?? FontWeight.w400,
        color: color ?? Colors.black,
        overflow: overflow,

      ),

    );
  }

}