import 'package:flutter/cupertino.dart';
import 'package:get/get.dart';

class BottomNavigationController extends GetxController{
  var currIndex = 0.obs;
  PageController pageController = PageController();
  void pageChange(int index){
    currIndex.value = index;
    pageController.jumpToPage(index);
  }
}