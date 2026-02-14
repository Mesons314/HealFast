
import 'dart:convert';
import 'dart:core';
import 'package:healfast01/Models/clinicModels.dart';
import 'package:http/http.dart' as http;
import 'package:shared_preferences/shared_preferences.dart';
import '../Models/user_login.dart';

class ClinicApi{

  final String? baseUrl = 'http://192.168.0.106:8080/api';

  Future<void> addClinic(ClinicModel clinic) async {
    final res = await http.post(
      Uri.parse("$baseUrl/register/clinic"),
      headers: {'Content-Type':'application/json'},
      body: jsonEncode(clinic.toJson())
    );
    print('Status Code: ${res.statusCode}');
    print('Response Body: ${res.body}');
    if (res.statusCode == 200 || res.statusCode == 201) {
      print('Registration successful');
    }else{
      print('Status Code: ${res.statusCode}');
      print('Response Body: ${res.body}');
      throw Exception("Failed to register user");
    }
  }

  Future<String?> loginClinic(LoginRequest login) async{
    print('Calling backend at: $baseUrl');

    final res = await http.post(
      Uri.parse('$baseUrl/login/clinic'),
      headers:{'Content-Type':'application/json'},
      body: jsonEncode(login.toJson())
    );

    if(res.statusCode >= 200 && res.statusCode < 300){
      final Map<String,dynamic> data = jsonDecode(res.body);
      String accessToken = data['accessToken'];
      String refreshToken = data['refreshToken'];
      final prefs = await SharedPreferences.getInstance();
      await prefs.setString('accessToken', accessToken);
      await prefs.setString('refreshToken', refreshToken);
      return accessToken;
    }else{
      print('Status Code: ${res.statusCode}');
      print('Response Body: ${res.body}');
      throw Exception("Failed to register user");
    }
  }

  Future<List<ClinicModel>> fetchClinic() async{
    final SharedPreferences pref = await SharedPreferences.getInstance();
    final  token = pref.get('refreshToken');

    if (token == null) {
      throw Exception("No access token found. Please login again.");
    }

    final res = await http.get(
      Uri.parse("$baseUrl/clinic/clinicData"),
      headers: {
        'Content-Type':'application/json',
        'Authorization': 'Bearer $token'
      },
    );
    if(res.statusCode==200 ){
      List<dynamic> val = jsonDecode(res.body);
      List<ClinicModel> clinic = val.map((e) => ClinicModel.fromJson(e)).toList();
      return clinic;
    }
    else{
      throw Exception(
          "Failed to fetch clinics. Status Code: ${res.statusCode}, Body: ${res.body}"
      );
    }
  }

}