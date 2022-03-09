package com.example.domain.form;

import java.io.Serializable;
import java.time.LocalTime;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

/**
 * 予約入力フォームクラス
 *
 */
@Data
public class ReservationForm implements Serializable {

	private static final long serialVersionUID = 3L;
	
	/**会議開始時間*/
	@NotNull(message = "必須です")
	@DateTimeFormat(pattern = "HH:mm")
	private LocalTime startTime;
	
	/**会議終了時間*/
	@NotNull(message = "必須です")
	@DateTimeFormat(pattern = "HH:mm")
	private LocalTime endTime;
}
