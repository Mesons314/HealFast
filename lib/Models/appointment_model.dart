
class AppointmentModel {
  final int? id;
  final int? patientNo;
  final String? patientName;
  final String? patientAge;
  final String? clinicName;
  final String? patientPhoneNo;
  final String? clinicAddress;
  final String? date;

  AppointmentModel({
    this.id,
    this.patientNo,
    this.patientName,
    this.patientAge,
    this.patientPhoneNo,
    this.clinicName,
    this.clinicAddress,
    this.date
  });

  factory AppointmentModel.fromJson(Map<String, dynamic> json) {
    return AppointmentModel(
      id: json['id'] as int?,
      patientNo: json['patientNo'] as int?,
      patientName: json['patientName'] as String?,
      patientAge: json['patientAge'] as String?,
      patientPhoneNo: json['patientPhoneNo'] as String?,
      clinicName: json['clinicName'] as String?,
      clinicAddress: json['clinicAddress'] as String?,
      date: json['date'] as String?
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'id': id,
      'patientNo': patientNo,
      'patientName': patientName,
      'patientAge': patientAge,
      'patientPhoneNo': patientPhoneNo,
      'clinicName': clinicName,
      'clinicAddress': clinicAddress,
      'date':date
    };
  }
}
