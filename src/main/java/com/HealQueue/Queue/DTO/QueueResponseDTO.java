package com.HealQueue.Queue.DTO;

import com.HealQueue.Queue.Model.StatusEnum;

public class QueueResponseDTO {
    private long appointmentId;
    private int position;
    private int patientsAhead;
    private int estimatedWaitTime;
    private StatusEnum status;

    public QueueResponseDTO(long appointmentId, int estimatedWaitTime, int patientsAhead, int position, StatusEnum status) {
        this.appointmentId = appointmentId;
        this.estimatedWaitTime = estimatedWaitTime;
        this.patientsAhead = patientsAhead;
        this.position = position;
        this.status = status;
    }

    public long getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(long appointmentId) {
        this.appointmentId = appointmentId;
    }

    public int getEstimatedWaitTime() {
        return estimatedWaitTime;
    }

    public void setEstimatedWaitTime(int estimatedWaitTime) {
        this.estimatedWaitTime = estimatedWaitTime;
    }

    public int getPatientsAhead() {
        return patientsAhead;
    }

    public void setPatientsAhead(int patientsAhead) {
        this.patientsAhead = patientsAhead;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }
}
