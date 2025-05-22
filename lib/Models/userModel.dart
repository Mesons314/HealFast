import 'dart:ffi';

class UserModel{
  String? userName;
  String? gender;
  String? dob;
  String? password;
  String? firstName;
  String? lastName;
  String? address;
  String? phoneNo;
  String? role;
  Long? id;

  UserModel({required this.userName,required this.gender,required this.dob,required this.password,required this.firstName,
    required this.lastName,required this.address,this.id, required this.role, required this.phoneNo});

  factory UserModel.fromJson(Map<String , dynamic> json){
    return UserModel(
        userName: json['userName'],
        gender:json['gender'] ,
        dob: json['dob'],
        password: json['password'],
        firstName: json['firstName'],
        lastName: json['lastName'],
        address : json['address'],
        phoneNo: json['phoneNo'],
        role: json['role']
    );
  }

   Map<String, dynamic> toJson()=>{
     'userName': userName,
     'password': password,
     'address': address,
     'gender': gender,
     'firstName': firstName,
     'lastName': lastName,
     'dob': dob,
     'phoneNo': phoneNo
  };
}