import 'dart:convert';
import 'package:healfast01/Models/clinicModels.dart';
import 'package:healfast01/Models/userModel.dart';
import 'package:healfast01/Models/user_login.dart';
import 'package:http/http.dart' as http;
import 'package:jwt_decoder/jwt_decoder.dart';
import 'package:shared_preferences/shared_preferences.dart';
class UserAPI{
  final String? baseUrl = 'http://192.168.0.106:8080/api';

  Future<void> addUser(UserModel user) async {
    print(user.phoneNo);
    print(user.firstName);
    print(user.userName);

    final res = await http.post(
      Uri.parse("$baseUrl/register/user"),
      headers: {'Content-Type': 'application/json'},
      body: jsonEncode(user.toJson()),
    );
    print(res.body);
    if (res.statusCode == 200 || res.statusCode == 201) {
      print('Registration successful');
    } else if (res.statusCode == 409) {
      print('Username already exists');
    } else{
      print('Status Code: ${res.statusCode}');
      print('Response Body: ${res.body}');
      throw Exception("Failed to register user");
    }
  }

  Future<String?> loginUser(LoginRequest login) async{
    final res = await http.post(
      Uri.parse("$baseUrl/login/user"),
      headers: {'Content-Type': 'application/json'},
      body: jsonEncode(login.toJson())
    );
    if (res.statusCode == 200) {
      print('Response body: ${res.body}');
      // final token = res.body; // use directly, no jsonDecode
      final Map<String,dynamic> data = jsonDecode(res.body);
      final String accessToken = data['accessToken'];
      final String refreshToken = data['refreshToken'];

      final prefs = await SharedPreferences.getInstance();
      await prefs.setString('accessToken', accessToken);
      await prefs.setString('refreshToken', refreshToken);

      Map<String,dynamic> decode = JwtDecoder.decode(accessToken);
      String role = decode['role'];
      return accessToken;
    }else {
      print('Login Failed: ${res.statusCode} - ${res.body}');
      return null;
    }
  }

  Future<UserModel?> getUserById(int id,String token) async{
    final res = await http.get(
      Uri.parse("$baseUrl/user/getUser/$id"),
      headers: {
        'Content-Type': 'application/json',
        'Authorization': 'Bearer $token'
      }
    );
    if (res.statusCode == 200) {
      final data = jsonDecode(res.body);
      return UserModel.fromJson(data);
    } else {
      print("Failed to fetch user: ${res.statusCode}");
      return null;
    }
  }

  Future<List<ClinicModel>> getClinic() async{
    final pref = await SharedPreferences.getInstance();
    final token = pref.getString('accessToken');
    if (token == null) {
      throw Exception("No access token found. Please login again.");
    }

    final res = await http.get(
      Uri.parse('$baseUrl/clinic/clinicData'),
      headers: {'Content-Type':'application/json',
        'Authorization':'Bearer $token'
      }
    );

    if(res.statusCode == 200){
      print(res.body);
      final List<dynamic> val = jsonDecode(res.body);
      List<ClinicModel> data = val.map((e)=> ClinicModel.fromJson(e)).toList();
      return data;
    }else{
      throw Exception("Fetching data failed ${res.statusCode} and ${res.body}");
    }
  }
}