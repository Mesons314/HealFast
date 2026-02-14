import 'package:get/get.dart';
import 'package:healfast01/Models/user_login.dart';
import 'package:healfast01/utils/text_editing_controller.dart';
import '../API/ClinicAPI.dart';
import '../API/UserAPI.dart';

class LoginController extends GetxController{
  RxBool passwordVisible = true.obs;
  final repo = ClinicApi();
  RxString username = "".obs;
  RxString password = "".obs;
  RxBool isLoading = false.obs;

  final controller = textEditingController();
  void togglePassword(){
    passwordVisible.value = !passwordVisible.value;
  }

  Future<void> loginController() async{
    if (controller.username.text.isEmpty || controller.password.text.isEmpty) {
      Get.snackbar("Error", "Please enter both username and password");
      return;
    }
    isLoading.value = true;
    // Get.toNamed('/bottomNav');
    try{
      final login = LoginRequest(
        userName: controller.username.text,
        password: controller.password.text
      );
      String? accessToken = await repo.loginClinic(login);
      if(accessToken !=null){
      // Get.toNamed('/profile');
        Get.offNamed('/bottomNav');
      }

    }catch(e){
      Get.snackbar('Failed', 'Login Failed $e');
      print(e);
    }finally{
      isLoading.value = false;
    }
  }

  @override
  void onClose() {
    controller.username.dispose();
    controller.password.dispose();
    super.onClose();
  }
}