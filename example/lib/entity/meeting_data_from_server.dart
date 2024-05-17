class MeetingDataFromServer {
  String? token;
  String? server;
  String? ownerName;
  String? meetingID;
  bool? breakout;
  String? title;
  String? description;
  String? role;
  String? type;
  int? participantCount;
  bool? audioMuted;
  List<IceServers>? iceServers;
  bool? preferIceRelay;
  Config? config;
  Feature? feature;
  String? mediaServer;
  bool? secure;
  bool? authenticated;

  MeetingDataFromServer(
      {this.token,
        this.server,
        this.ownerName,
        this.meetingID,
        this.breakout,
        this.title,
        this.description,
        this.role,
        this.type,
        this.participantCount,
        this.audioMuted,
        this.iceServers,
        this.preferIceRelay,
        this.config,
        this.feature,
        this.mediaServer,
        this.secure,
        this.authenticated});

  MeetingDataFromServer.fromJson(Map<String, dynamic> json) {
    token = json['token'];
    server = json['server'];
    ownerName = json['ownerName'];
    meetingID = json['meetingID'];
    breakout = json['breakout'];
    title = json['title'];
    description = json['description'];
    role = json['role'];
    type = json['type'];
    participantCount = json['participantCount'];
    audioMuted = json['audioMuted'];
    if (json['iceServers'] != null) {
      iceServers = <IceServers>[];
      json['iceServers'].forEach((v) {
        iceServers!.add(IceServers.fromJson(v));
      });
    }
    preferIceRelay = json['preferIceRelay'];
    config =
    json['config'] != null ? Config.fromJson(json['config']) : null;
    feature =
    json['feature'] != null ? Feature.fromJson(json['feature']) : null;
    mediaServer = json['mediaServer'];
    secure = json['secure'];
    authenticated = json['authenticated'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = <String, dynamic>{};
    data['token'] = token;
    data['server'] = server;
    data['ownerName'] = ownerName;
    data['meetingID'] = meetingID;
    data['breakout'] = breakout;
    data['title'] = title;
    data['description'] = description;
    data['role'] = role;
    data['type'] = type;
    data['participantCount'] = participantCount;
    data['audioMuted'] = audioMuted;
    if (iceServers != null) {
      data['iceServers'] = iceServers!.map((v) => v.toJson()).toList();
    }
    data['preferIceRelay'] = preferIceRelay;
    if (config != null) {
      data['config'] = config!.toJson();
    }
    if (feature != null) {
      data['feature'] = feature!.toJson();
    }
    data['mediaServer'] = mediaServer;
    data['secure'] = secure;
    data['authenticated'] = authenticated;
    return data;
  }
}

class IceServers {
  String? urls;
  String? username;
  String? credential;

  IceServers({this.urls, this.username, this.credential});

  IceServers.fromJson(Map<String, dynamic> json) {
    urls = json['urls'];
    username = json['username'];
    credential = json['credential'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = <String, dynamic>{};
    data['urls'] = urls;
    data['username'] = username;
    data['credential'] = credential;
    return data;
  }
}

class Config {
  int? maxConferenceTimeoutMinutes;
  String? watermark;
  String? watermarkPosition;
  bool? disableNameTag;
  String? layout;
  bool? recording;
  bool? webinar;
  bool? videoFirst;
  bool? videoOnly;

  Config(
      {this.maxConferenceTimeoutMinutes,
        this.watermark,
        this.watermarkPosition,
        this.disableNameTag,
        this.layout,
        this.recording,
        this.webinar,
        this.videoFirst,
        this.videoOnly});

  Config.fromJson(Map<String, dynamic> json) {
    maxConferenceTimeoutMinutes = json['maxConferenceTimeoutMinutes'];
    watermark = json['watermark'];
    watermarkPosition = json['watermarkPosition'];
    disableNameTag = json['disableNameTag'];
    layout = json['layout'];
    recording = json['recording'];
    webinar = json['webinar'];
    videoFirst = json['videoFirst'];
    videoOnly = json['videoOnly'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = <String, dynamic>{};
    data['maxConferenceTimeoutMinutes'] = maxConferenceTimeoutMinutes;
    data['watermark'] = watermark;
    data['watermarkPosition'] = watermarkPosition;
    data['disableNameTag'] = disableNameTag;
    data['layout'] = layout;
    data['recording'] = recording;
    data['webinar'] = webinar;
    data['videoFirst'] = videoFirst;
    data['videoOnly'] = videoOnly;
    return data;
  }
}

class Feature {
  bool? recording;
  bool? passive;
  bool? webinar;
  bool? broadcast;
  bool? breakout;
  bool? multiBroadcast;
  bool? watermark;
  bool? documentCamera;
  bool? whiteboard;
  bool? desktopControl;

  Feature(
      {this.recording,
        this.passive,
        this.webinar,
        this.broadcast,
        this.breakout,
        this.multiBroadcast,
        this.watermark,
        this.documentCamera,
        this.whiteboard,
        this.desktopControl});

  Feature.fromJson(Map<String, dynamic> json) {
    recording = json['recording'];
    passive = json['passive'];
    webinar = json['webinar'];
    broadcast = json['broadcast'];
    breakout = json['breakout'];
    multiBroadcast = json['multiBroadcast'];
    watermark = json['watermark'];
    documentCamera = json['documentCamera'];
    whiteboard = json['whiteboard'];
    desktopControl = json['desktopControl'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = <String, dynamic>{};
    data['recording'] = recording;
    data['passive'] = passive;
    data['webinar'] = webinar;
    data['broadcast'] = broadcast;
    data['breakout'] = breakout;
    data['multiBroadcast'] = multiBroadcast;
    data['watermark'] = watermark;
    data['documentCamera'] = documentCamera;
    data['whiteboard'] = whiteboard;
    data['desktopControl'] = desktopControl;
    return data;
  }

}
