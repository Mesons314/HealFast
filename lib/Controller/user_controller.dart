import 'package:get/get.dart';
import 'package:healfast01/API/UserAPI.dart';
import 'package:healfast01/Mock%20Data/mock_clinic.dart';
import 'package:healfast01/Models/clinicModels.dart';
import 'package:healfast01/Models/userModel.dart';
import 'package:healfast01/utils/text_editing_controller.dart';

class UserController extends GetxController{
  textEditingController controller = textEditingController();
  var gender = ''.obs;
  var isLoading = false.obs;
  RxList<ClinicModel> clinicList = <ClinicModel>[].obs;
  var errorMessage = ''.obs;
  UserAPI api = UserAPI();

  Future<void> registerUser() async{
    try{
      UserModel userModel = UserModel(
        userName: controller.username.text,
        password: controller.password.text,
        address: controller.address.text,
        gender: gender.value,
        firstName: controller.firstName.text,
        lastName: controller.lastName.text,
        dob: controller.dob.text,
        phoneNo: controller.phoneNo.text,
        role: controller.role.text,
      );

      await api.addUser(userModel);

      Get.snackbar('Success', 'Registered Successfully');
      Get.offNamed('/bottomNav');
    }catch(e){
      Get.snackbar("Error", "Failed to register: $e");
      print("Error Failed to register: $e");
    }
  }

  Future<void> getClinicData() async{
    try {
      isLoading.value = true;
      final data = await api.getClinic();
      clinicList.assignAll(data);
    }catch (e) {
      errorMessage.value = "Failed to fetch clinic data: $e";
      Get.snackbar("Error", errorMessage.value);
    } finally {
      isLoading.value = false;
    }
  }

  void toClinic(int idx){
    Get.toNamed('/clinicInfo',arguments: idx);
  }
  ClinicModel getClinicById(int id){
    return mockClinics.firstWhere((c) => c.id == id);
  }
}