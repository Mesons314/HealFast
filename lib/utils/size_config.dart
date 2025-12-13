import 'package:flutter/material.dart';

class SizeConfig {
  static late bool isTablet;
  static late double screenWidth;
  static late double screenHeight;



  static void init(BuildContext context) {
    final mediaQuery = MediaQuery.of(context);
    screenWidth = mediaQuery.size.width;
    screenHeight = mediaQuery.size.height;
    isTablet = screenWidth >= 600;
  }

  /// All padding sizes adapt to device type
  static double get paddingXSmall => isTablet ? 8.0 : 4.0;
  static double get paddingXS => isTablet ? 12.0 : 8.0;
  static double get paddingXSL => isTablet ? 14.0 : 10.0;
  static double get paddingS => isTablet ? 16.0 : 12.0;
  static double get paddingM => isTablet ? 20.0 : 16.0;
  static double get paddingL => isTablet ? 28.0 : 20.0;
  static double get paddingXL => isTablet ? 36.0 : 24.0;

  static double get buttonXL => isTablet ? 30.0 : 40.0;

  ///FONT SIZE...
  static double get extraSmall => isTablet ? 14.0 : 10.0;
  static double get small => isTablet ? 16.0 : 12.0;
  static double get medium => isTablet ? 18.0 : 14.0;
  static double get large => isTablet ? 22.0 : 16.0;
  static double get extraLarge => isTablet ? 26.0 : 20.0;
  static double get extraLarge22 => isTablet ? 28.0 : 22.0;
  static double get title => isTablet ? 30.0 : 24.0;
  static double get heading => isTablet ? 36.0 : 28.0;

  static double get size1 => isTablet ? 2.0 : 1.0;
  static double get size2 => isTablet ? 4.0 : 2.0;
  static double get size3 => isTablet ? 6.0 : 3.0;
  static double get size4 => isTablet ? 8.0 : 4.0;
  static double get size5 => isTablet ? 10.0 : 5.0;
  static double get size6 => isTablet ? 12.0 : 6.0;
  static double get size8 => isTablet ? 16.0 : 8.0;
  static double get size10 => isTablet ? 20.0 : 10.0;
  static double get size12 => isTablet ? 24.0 : 12.0;
  static double get size20 => isTablet ? 30.0 : 20.0;
  static double get size22 => isTablet ? 32.0 : 22.0;
  static double get size15 => isTablet ? 25.0 : 15.0;
  static double get size24 => isTablet ? 34.0 : 24.0;
  static double get size25 => isTablet ? 35.0 : 25.0;
  static double get size26 => isTablet ? 36.0 : 26.0;
  static double get size28 => isTablet ? 38.0 : 28.0;
  static double get size30 => isTablet ? 40.0 : 30.0;
  static double get size32 => isTablet ? 42.0 : 32.0;
  static double get size35 => isTablet ? 45.0 : 35.0;
  static double get size36 => isTablet ? 46.0 : 36.0;
  static double get size37 => isTablet ? 47.0 : 37.0;
  static double get size38 => isTablet ? 48.0 : 38.0;
  static double get size40 => isTablet ? 50.0 : 40.0;
  static double get size45 => isTablet ? 55.0 : 45.0;
  static double get size50 => isTablet ? 60.0 : 50.0;
  static double get size60 => isTablet ? 70.0 : 60.0;
  static double get size65 => isTablet ? 75.0 : 65.0;
  static double get size80 => isTablet ? 90.0 : 80.0;
  static double get size90 => isTablet ? 100.0 : 90.0;
  static double get size100 => isTablet ? 110.0 : 100.0;
  static double get size110 => isTablet ? 120.0 : 110.0;
  static double get size200 => isTablet ? 250.0 : 200.0;
  static double get size220 => isTablet ? 300.0 : 220.0;
  static double get size250 => isTablet ? 350.0 : 250.0;
  static double get size55 => isTablet ? 65.0 : 55.0;
  static double get size57 => isTablet ? 67.0 : 57.0;
  static double get size120 => isTablet ? 130.0 : 120.0;
  static double get size300 => isTablet ? 310.0 : 300.0;
  static double get size400 => isTablet ? 410.0 : 400.0;
  static double get size130 => isTablet ? 145.0 : 130.0;
  static double get size140 => isTablet ? 160.0 : 140.0;
  static double get size150 => isTablet ? 170.0 : 150.0;
  static double get size160 => isTablet ? 180.0 : 160.0;
  static double get size190 => isTablet ? 220.0 : 190.0;

  //My Size
  static double get size358 => isTablet ? 378.0 : 358.0;
  static double get size70 => isTablet ? 80 :70;
  static double get size17 => isTablet ? 27 : 17;
  static double get size18 => isTablet ? 28 : 18;
  static double get size75 => isTablet ? 85 : 75;
  static double get size43 => isTablet ? 53 : 43;
  static double get size14 => isTablet ? 24 : 14;
  static double get size175 => isTablet ? 195.0 : 175.0;
  static double get size360 => isTablet ? 380.0 : 360.0;
  static double get size570 => isTablet ? 590.0 : 570.0;
  static double get size124 => isTablet ? 134.0 : 124.0;
  static double get size76 => isTablet ? 86.0 : 76.0;
  static double get size84 => isTablet ? 94.0 : 84.0;
  static double get size106 => isTablet ? 116.0 : 106.0;
  static double get size68 => isTablet ? 78.0 :68.0;
  static double get size180 => isTablet ? 200.0 :180.0;

}