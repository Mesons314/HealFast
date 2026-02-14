class UserModel {
  int? id;
  String? userName;
  String? password;
  String? address;
  String? gender;
  String? firstName;
  String? lastName;
  String? dob;
  String? phoneNo;
  String? role;

  UserModel({
    this.id,
    required this.userName,
    required this.password,
    required this.address,
    required this.gender,
    required this.firstName,
    required this.lastName,
    required this.dob,
    required this.phoneNo,
    required this.role,
  });

  factory UserModel.fromJson(Map<String, dynamic> json) {
    return UserModel(
      id: json['id'],
      userName: json['userName'],
      password: json['password'],
      address: json['address'],
      gender: json['gender'],
      firstName: json['firstName'],
      lastName: json['lastName'],
      dob: json['dob'],
      phoneNo: json['phoneNo'],
      role: json['role'],
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'id': id,
      'userName': userName,
      'password': password,
      'address': address,
      'gender': gender,
      'firstName': firstName,
      'lastName': lastName,
      'dob': dob,
      'phoneNo': phoneNo,
      'role': role,
    };
  }
}
