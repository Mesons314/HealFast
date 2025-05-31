class ClinicModel{
  String? userName;
  String? password;
  String? address;
  String? graduationDegree;
  String? firstName;
  String? lastName;
  String? phoneNo;
  String? speciality;
  String? gender;
  String? dob;
  String? clinicName;
  String? clinicPhoneNo;
  String? role;

  ClinicModel({
      this.userName,
      this.password,
      this.address,
      this.graduationDegree,
      this.firstName,
      this.lastName,
      this.phoneNo,
      this.speciality,
      this.gender,
      this.dob,
      this.clinicName,
      this.clinicPhoneNo,
      this.role});

  factory ClinicModel.fromJson(Map<String,dynamic> json){
    return ClinicModel(
        userName: json['userName'],
        gender:json['gender'] ,
        dob: json['dob'],
        password: json['password'],
        firstName: json['firstName'],
        lastName: json['lastName'],
        address : json['address'],
        phoneNo: json['phoneNo'],
        clinicName: json['clinicName'],
        clinicPhoneNo: json['clinicPhoneNo'],
        speciality: json['speciality'],
        graduationDegree: json['graduationDegree'],
        role: json['role']
    );
  }

  Map<String,dynamic> toJson()=>{
    'userName': userName,
    'password': password,
    'address': address,
    'gender': gender,
    'firstName': firstName,
    'lastName': lastName,
    'dob': dob,
    'phoneNo': phoneNo,
    'clinicName': clinicName,
    'clinicPhoneNo': clinicPhoneNo,
    'speciality' : speciality,
    'graduationDegree': graduationDegree,
    'role':role
  };
}