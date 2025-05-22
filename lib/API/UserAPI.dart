import 'dart:convert';
import 'package:healfast01/Models/userModel.dart';
import 'package:http/http.dart' as http;
class UserCall{
  final String? baseUrl = 'http://192.168.0.105:8080/api';

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
}