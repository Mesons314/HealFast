class LoginRequest{
  String? userName;
  String? password;

  LoginRequest({this.userName,this.password});

  Map<String,dynamic> toJson()=>{
    'userName': userName,
    'password': password
  };
}