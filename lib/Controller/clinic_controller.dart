import 'package:get/get.dart';
import 'package:healfast01/API/ClinicAPI.dart';
import 'package:healfast01/Models/clinicModels.dart';
import 'package:healfast01/utils/text_editing_controller.dart';

class ClinicController extends GetxController{

  textEditingController controller = textEditingController();
  var gender = ''.obs;

  ClinicApi api = ClinicApi();

  Future<void> registerDoctor()async{
    try{
      ClinicModel clinicModel = ClinicModel(
        userName: controller.username.text,
        password: controller.password.text,
        address: controller.address.text,
        graduationDegree: controller.graduationDegree.text,
        firstName: controller.firstName.text,
        lastName: controller.lastName.text,
        phoneNo: controller.phoneNo.text,
        clinicName: controller.clinicName.text,
        clinicPhoneNo: controller.clinicPhoneNo.text,
        gender: gender.value,
        speciality: controller.speciality.text,
        dob: controller.dob.text,
        role: controller.role.text
      );

      await api.addClinic(clinicModel);
      Get.snackbar("Success", "Registered Successfully");
      Get.offAllNamed('/clinicHome');
    }catch(e){
      Get.snackbar("Error", "Failed to register: $e");
      print("Error Failed to register: $e");
    }
  }
}