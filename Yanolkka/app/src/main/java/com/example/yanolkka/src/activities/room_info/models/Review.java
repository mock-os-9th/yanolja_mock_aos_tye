package com.example.yanolkka.src.activities.room_info.models;

public class Review {
    private int UserIdx;
    private int ReviewIdx;
    private String UserName;
    private String RoomName;
    private String ReserveType;
    private String ReviewContent;
    private int OverallRating;
    private String WrittenTime;
    private String ReviewPhoto;
    private String ReviewReply;
    private String ReplyWrittenTime;

    public int getUserIdx() {
        return UserIdx;
    }

    public int getReviewIdx() {
        return ReviewIdx;
    }

    public String getUserName() {
        return UserName;
    }

    public String getReserveType() {
        return ReserveType;
    }

    public String getReviewContent() {
        return ReviewContent;
    }

    public float getOverallRating() {
        return OverallRating;
    }

    public String getReviewPhoto() {
        return ReviewPhoto;
    }

    public String getReviewReply() {
        return ReviewReply;
    }

    public String getRoomName() {
        return RoomName;
    }

    public String getWrittenTime() {
        return WrittenTime;
    }

    public String getReplyWrittenTime() {
        return ReplyWrittenTime;
    }
}
