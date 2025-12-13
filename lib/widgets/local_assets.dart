import 'package:flutter/cupertino.dart';

class LocalAssets extends StatelessWidget{
  final String imagePath;
  final double? height,width;
  final Color? imgColor;
  final BoxFit? boxFit;

  LocalAssets(
  {Key? key,
    required this.imagePath,
    this.height,
    this.width,
    this.boxFit,
    this.imgColor
  });
  @override
  Widget build(BuildContext context) {
   return Image.asset(
    imagePath,
    width: width,
    height: height,
    fit: boxFit ?? BoxFit.contain,
    color: imgColor,
   );
  }
}