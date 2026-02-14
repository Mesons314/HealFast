class ClinicModel {
  final int? id;
  final String? userName;
  final String? password;
  final String? address;
  final String? graduationDegree;
  final String? firstName;
  final String? lastName;
  final String? speciality;
  final String? gender;
  final String? dob;
  final String? clinicName;
  final String? phoneNo;
  final String? clinicPhoneNo;
  final String? role;

  ClinicModel({
    this.id,
    this.userName,
    this.password,
    this.address,
    this.graduationDegree,
    this.firstName,
    this.lastName,
    this.speciality,
    this.gender,
    this.dob,
    this.clinicName,
    this.phoneNo,
    this.clinicPhoneNo,
    this.role,
  });

  factory ClinicModel.fromJson(Map<String, dynamic> json) {
    return ClinicModel(
      id: json['id'],
      userName: json['userName'],
      password: json['password'],
      address: json['address'],
      graduationDegree: json['graduationDegree'],
      firstName: json['firstName'],
      lastName: json['lastName'],
      speciality: json['speciality'],
      gender: json['gender'],
      dob: json['dob'],
      clinicName: json['clinicName'],
      phoneNo: json['phoneNo'],
      clinicPhoneNo: json['clinicPhoneNo'],
      role: json['role'],
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'id': id,
      'userName': userName,
      'password': password,
      'address': address,
      'graduationDegree': graduationDegree,
      'firstName': firstName,
      'lastName': lastName,
      'speciality': speciality,
      'gender': gender,
      'dob': dob,
      'clinicName': clinicName,
      'phoneNo': phoneNo,
      'clinicPhoneNo': clinicPhoneNo,
      'role': role,
    };
  }
}
