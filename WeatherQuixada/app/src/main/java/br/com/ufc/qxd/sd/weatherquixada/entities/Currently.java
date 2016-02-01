package br.com.ufc.qxd.sd.weatherquixada.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by kaynan on 31/01/16.
 */
public class Currently implements Parcelable{

    @SerializedName("id")
    private Integer id;
    @SerializedName("summary")
    private String summary;
    @SerializedName("icon")
    private String icon;
    @SerializedName("precip_intensity")
    private Double precipIntensity;
    @SerializedName("precip_probability")
    private Double precipProbability;
    @SerializedName("precip_type")
    private String precipType;
    @SerializedName("temperature")
    private Double temperature;
    @SerializedName("apparent_temperature")
    private Double apparentTemperature;
    @SerializedName("humidity")
    private Double humidity;
    @SerializedName("wind_speed")
    private Double windSpeed;
    @SerializedName("cloud_cover")
    private Double cloudCover;
    @SerializedName("pressure")
    private Double pressure;
    @SerializedName("ozone")
    private Double ozone;

    public Currently() {
    }

    public Currently(Integer id, String summary, String icon, Double precipIntensity, Double precipProbability, String precipType, Double temperature, Double apparentTemperature, Double humidity, Double windSpeed, Double cloudCover, Double pressure, Double ozone) {
        this.id = id;
        this.summary = summary;
        this.icon = icon;
        this.precipIntensity = precipIntensity;
        this.precipProbability = precipProbability;
        this.precipType = precipType;
        this.temperature = temperature;
        this.apparentTemperature = apparentTemperature;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.cloudCover = cloudCover;
        this.pressure = pressure;
        this.ozone = ozone;
    }


    protected Currently(Parcel in) {
        id = in.readInt();
        summary = in.readString();
        icon = in.readString();
        precipIntensity = in.readDouble();
        precipProbability = in.readDouble();
        precipType = in.readString();
        temperature = in.readDouble();
        apparentTemperature = in.readDouble();
        humidity = in.readDouble();
        windSpeed = in.readDouble();
        cloudCover = in.readDouble();
        pressure = in.readDouble();
        ozone = in.readDouble();
    }

    public static final Creator<Currently> CREATOR = new Creator<Currently>() {
        @Override
        public Currently createFromParcel(Parcel in) {
            return new Currently(in);
        }

        @Override
        public Currently[] newArray(int size) {
            return new Currently[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(summary);
        dest.writeString(icon);
        dest.writeDouble(precipIntensity);
        dest.writeDouble(precipProbability);
        dest.writeString(precipType);
        dest.writeDouble(temperature);
        dest.writeDouble(apparentTemperature);
        dest.writeDouble(humidity);
        dest.writeDouble(windSpeed);
        dest.writeDouble(cloudCover);
        dest.writeDouble(pressure);
        dest.writeDouble(ozone);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Double getPrecipIntensity() {
        return precipIntensity;
    }

    public void setPrecipIntensity(Double precipIntensity) {
        this.precipIntensity = precipIntensity;
    }

    public Double getPrecipProbability() {
        return precipProbability;
    }

    public void setPrecipProbability(Double precipProbability) {
        this.precipProbability = precipProbability;
    }

    public String getPrecipType() {
        return precipType;
    }

    public void setPrecipType(String precipType) {
        this.precipType = precipType;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Double getApparentTemperature() {
        return apparentTemperature;
    }

    public void setApparentTemperature(Double apparentTemperature) {
        this.apparentTemperature = apparentTemperature;
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    public Double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(Double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public Double getCloudCover() {
        return cloudCover;
    }

    public void setCloudCover(Double cloudCover) {
        this.cloudCover = cloudCover;
    }

    public Double getPressure() {
        return pressure;
    }

    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }

    public Double getOzone() {
        return ozone;
    }

    public void setOzone(Double ozone) {
        this.ozone = ozone;
    }

    @Override
    public String toString() {
        return "Currently{" +
                "id=" + id +
                ", summary='" + summary + '\'' +
                ", icon='" + icon + '\'' +
                ", precipIntensity=" + precipIntensity +
                ", precipProbability=" + precipProbability +
                ", precipType='" + precipType + '\'' +
                ", temperature=" + temperature +
                ", apparentTemperature=" + apparentTemperature +
                ", humidity=" + humidity +
                ", windSpeed=" + windSpeed +
                ", cloudCover=" + cloudCover +
                ", pressure=" + pressure +
                ", ozone=" + ozone +
                '}';
    }
}
