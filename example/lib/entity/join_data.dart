class JoinData {
  String? token;
  int? expiryInSec;
  String? meetingID;
  String? title;
  String? description;
  String? role;
  String? type;
  String? nonce;
  String? validFrom;
  String? validTill;

  JoinData(
      {this.token,
        this.expiryInSec,
        this.meetingID,
        this.title,
        this.description,
        this.role,
        this.type,
        this.nonce,
        this.validFrom,
        this.validTill});

  JoinData.fromJson(Map<String, dynamic> json) {
    token = json['token'];
    expiryInSec = json['expiryInSec'];
    meetingID = json['meetingID'];
    title = json['title'];
    description = json['description'];
    role = json['role'];
    type = json['type'];
    nonce = json['nonce'];
    validFrom = json['validFrom'];
    validTill = json['validTill'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = <String, dynamic>{};
    data['token'] = token;
    data['expiryInSec'] = expiryInSec;
    data['meetingID'] = meetingID;
    data['title'] = title;
    data['description'] = description;
    data['role'] = role;
    data['type'] = type;
    data['nonce'] = nonce;
    data['validFrom'] = validFrom;
    data['validTill'] = validTill;
    return data;
  }
}
