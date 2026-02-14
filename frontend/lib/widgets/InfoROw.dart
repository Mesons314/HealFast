import 'package:flutter/material.dart';

import 'common_text.dart';

class InfoRow extends StatelessWidget {
  final String label;
  final String value;

  const InfoRow({Key? key, required this.label, required this.value})
      : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.symmetric(vertical: 4.0),
      child: Row(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          CommonText(
            text: '$label: ',
            fontWeight: FontWeight.w600,
            fontSize: 14,
          ),
          Expanded(
            child: CommonText(
              text: value,
              fontWeight: FontWeight.w400,
              fontSize: 14,
            ),
          ),
        ],
      ),
    );
  }
}