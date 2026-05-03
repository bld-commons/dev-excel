/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class com.bld.generator.report.excel.data.ExtraColumnAnnotation.java
*/
package com.bld.generator.report.excel.data;

import java.util.function.Consumer;

import com.bld.common.spreadsheet.excel.annotation.ExcelBooleanText;
import com.bld.common.spreadsheet.excel.annotation.ExcelDate;
import com.bld.generator.report.excel.annotation.ExcelCellLayout;
import com.bld.generator.report.excel.annotation.ExcelColumn;
import com.bld.generator.report.excel.annotation.ExcelColumnWidth;
import com.bld.generator.report.excel.annotation.ExcelDataValidation;
import com.bld.generator.report.excel.annotation.ExcelDropDown;
import com.bld.generator.report.excel.annotation.ExcelFunction;
import com.bld.generator.report.excel.annotation.ExcelHeaderCellLayout;
import com.bld.generator.report.excel.annotation.ExcelImage;
import com.bld.generator.report.excel.annotation.ExcelMergeRow;
import com.bld.generator.report.excel.annotation.ExcelNumberFormat;
import com.bld.generator.report.excel.annotation.ExcelSubtotal;
import com.bld.generator.report.excel.annotation.impl.ExcelBooleanTextImpl;
import com.bld.generator.report.excel.annotation.impl.ExcelCellLayoutImpl;
import com.bld.generator.report.excel.annotation.impl.ExcelColumnImpl;
import com.bld.generator.report.excel.annotation.impl.ExcelColumnWidthImpl;
import com.bld.generator.report.excel.annotation.impl.ExcelDataValidationImpl;
import com.bld.generator.report.excel.annotation.impl.ExcelDateImpl;
import com.bld.generator.report.excel.annotation.impl.ExcelDropDownImpl;
import com.bld.generator.report.excel.annotation.impl.ExcelFunctionImpl;
import com.bld.generator.report.excel.annotation.impl.ExcelHeaderCellLayoutImpl;
import com.bld.generator.report.excel.annotation.impl.ExcelImageImpl;
import com.bld.generator.report.excel.annotation.impl.ExcelMergeRowImpl;
import com.bld.generator.report.excel.annotation.impl.ExcelNumberFormatImpl;
import com.bld.generator.report.excel.annotation.impl.ExcelSubtotalImpl;

import jakarta.validation.constraints.NotNull;
/**
 * The Class ExtraColumnAnnotation.<br>
 * ExtraColumnAnnotation is used to configure the dynamic cells type and the
 * style.<br>
 * It must be set on mapExtraColumnAnnotation within the
 * {@link com.bld.generator.report.excel.SheetDynamicData} classes.
 */
public class ExtraColumnAnnotation {

	/**
	 * The excel column.<br>
	 * It is not null
	 */
	@NotNull
	private ExcelColumn excelColumn;

	/**
	 * The excel cell layout. <br>
	 * it is not null.
	 */
	@NotNull
	private ExcelCellLayout excelCellLayout;

	/** The excel date. */
	private ExcelDate excelDate;

	/** The excel data validation. */
	private ExcelDataValidation excelDataValidation;

	/** The excel column width. */
	private ExcelColumnWidth excelColumnWidth;

	/** The excel merge row. */
	private ExcelMergeRow excelMergeRow;

	/** The excel function. */
	private ExcelFunction excelFunction;

	/** The excel header layout. */
	private ExcelHeaderCellLayout excelHeaderCellLayout;

	/** The excel drop down. */
	private ExcelDropDown excelDropDown;

	/** The excel subtotal. */
	private ExcelSubtotal excelSubtotal;

	/** The excel boolean text. */
	private ExcelBooleanText excelBooleanText;

	/** The excel number format. */
	private ExcelNumberFormat excelNumberFormat;

	/** The excel image. */
	private ExcelImage excelImage;

	/**
	 * Sets the excel cell layout.
	 *
	 * @param excelCellLayoutImpl the new excel cell layout
	 */
	public void setExcelCellLayout(ExcelCellLayoutImpl excelCellLayoutImpl) {
		if (excelCellLayoutImpl != null)
			this.excelCellLayout = excelCellLayoutImpl.getAnnotation();
	}

	/**
	 * Sets the excel boolean text.
	 *
	 * @param excelBooleanTextImpl the new excel boolean text
	 */
	public void setExcelBooleanText(ExcelBooleanTextImpl excelBooleanTextImpl) {
		if (excelBooleanTextImpl != null)
			this.excelBooleanText = excelBooleanTextImpl.getAnnotation();
	}

	/**
	 * Sets the excel date.
	 *
	 * @param excelDateImpl the new excel date
	 */
	public void setExcelDate(ExcelDateImpl excelDateImpl) {
		if (excelDateImpl != null)
			this.excelDate = excelDateImpl.getAnnotation();
	}

	/**
	 * Sets the excel column.
	 *
	 * @param excelColumnImpl the new excel column
	 */
	public void setExcelColumn(ExcelColumnImpl excelColumnImpl) {
		if (excelColumnImpl != null)
			this.excelColumn = excelColumnImpl.getAnnotation();
	}

	/**
	 * Sets the excel function.
	 *
	 * @param excelFunctionImpl the new excel function
	 */
	public void setExcelFunction(ExcelFunctionImpl excelFunctionImpl) {
		if (excelFunctionImpl != null)
			this.excelFunction = excelFunctionImpl.getAnnotation();
	}

	/**
	 * Sets the excel merge row.
	 *
	 * @param excelMergeRowImpl the new excel merge row
	 */
	public void setExcelMergeRow(ExcelMergeRowImpl excelMergeRowImpl) {
		if (excelMergeRowImpl != null)
			this.excelMergeRow = excelMergeRowImpl.getAnnotation();
	}

	/**
	 * Sets the excel header layout.
	 *
	 * @param excelHeaderCellLayoutImpl the new excel header layout
	 */
	public void setExcelHeaderCellLayout(ExcelHeaderCellLayoutImpl excelHeaderCellLayoutImpl) {
		if (excelHeaderCellLayoutImpl != null)
			this.excelHeaderCellLayout = excelHeaderCellLayoutImpl.getAnnotation();
	}

	/**
	 * Sets the excel column width.
	 *
	 * @param excelColumnWidthImpl the new excel column width
	 */
	public void setExcelColumnWidth(ExcelColumnWidthImpl excelColumnWidthImpl) {
		if (excelColumnWidthImpl != null)
			this.excelColumnWidth = excelColumnWidthImpl.getAnnotation();
	}

	/**
	 * Sets the excel drop down.
	 *
	 * @param excelDropDown the new excel drop down
	 */
	public void setExcelDropDown(ExcelDropDownImpl excelDropDown) {
		if (excelDropDown != null)
			this.excelDropDown = excelDropDown.getAnnotation();
	}

	/**
	 * Sets the excel data validation.
	 *
	 * @param excelDataValidation the new excel data validation
	 */
	public void setExcelDataValidation(ExcelDataValidationImpl excelDataValidation) {
		if (excelDataValidation != null)
			this.excelDataValidation = excelDataValidation.getAnnotation();
	}

	/**
	 * Gets the excel subtotal.
	 *
	 * @return the excel subtotal
	 */
	public ExcelSubtotal getExcelSubtotal() {
		return excelSubtotal;
	}

	/**
	 * Sets the excel subtotal.
	 *
	 * @param excelSubtotal the new excel subtotal
	 */
	public void setExcelSubtotal(ExcelSubtotalImpl excelSubtotal) {
		if (excelSubtotal != null)
			this.excelSubtotal = excelSubtotal.getAnnotation();
	}

	/**
	 * Gets the excel column.
	 *
	 * @return the excel column
	 */
	public ExcelColumn getExcelColumn() {
		return excelColumn;
	}

	/**
	 * Gets the excel cell layout.
	 *
	 * @return the excel cell layout
	 */
	public ExcelCellLayout getExcelCellLayout() {
		return excelCellLayout;
	}

	/**
	 * Gets the excel date.
	 *
	 * @return the excel date
	 */
	public ExcelDate getExcelDate() {
		return excelDate;
	}

	/**
	 * Gets the excel column width.
	 *
	 * @return the excel column width
	 */
	public ExcelColumnWidth getExcelColumnWidth() {
		return excelColumnWidth;
	}

	/**
	 * Gets the excel merge row.
	 *
	 * @return the excel merge row
	 */
	public ExcelMergeRow getExcelMergeRow() {
		return excelMergeRow;
	}

	/**
	 * Gets the excel function.
	 *
	 * @return the excel function
	 */
	public ExcelFunction getExcelFunction() {
		return excelFunction;
	}

	/**
	 * Gets the excel header layout.
	 *
	 * @return the excel header layout
	 */
	public ExcelHeaderCellLayout getExcelHeaderCellLayout() {
		return excelHeaderCellLayout;
	}

	/**
	 * Gets the excel drop down.
	 *
	 * @return the excel drop down
	 */
	public ExcelDropDown getExcelDropDown() {
		return excelDropDown;
	}

	/**
	 * Gets the excel boolean text.
	 *
	 * @return the excel boolean text
	 */
	public ExcelBooleanText getExcelBooleanText() {
		return excelBooleanText;
	}

	/**
	 * Gets the excel data validation.
	 *
	 * @return the excelDataValidation
	 */
	public ExcelDataValidation getExcelDataValidation() {
		return excelDataValidation;
	}

	/**
	 * Gets the excel number format.
	 *
	 * @return the excelNumberFormat
	 */
	public ExcelNumberFormat getExcelNumberFormat() {
		return excelNumberFormat;
	}

	/**
	 * Sets the excel number format.
	 *
	 * @param excelNumberFormat the new excel number format
	 */
	public void setExcelNumberFormat(ExcelNumberFormatImpl excelNumberFormat) {
		if (excelNumberFormat != null)
			this.excelNumberFormat = excelNumberFormat.getAnnotation();
	}

	/**
	 * Gets the excel image.
	 *
	 * @return the excelImage
	 */
	public ExcelImage getExcelImage() {
		return excelImage;
	}

	/**
	 * Sets the excel image.
	 *
	 * @param excelImage the new excel image
	 */
	public void setExcelImage(ExcelImageImpl excelImage) {
		if (excelImage != null)
			this.excelImage = excelImage.getAnnotation();
	}

	/**
	 * Sets the excel cell layout via a configurator lambda.
	 *
	 * @param consumer the cell layout configurator
	 */
	public void setExcelCellLayout(Consumer<ExcelCellLayoutImpl> consumer) {
		ExcelCellLayoutImpl impl = new ExcelCellLayoutImpl();
		consumer.accept(impl);
		this.excelCellLayout = impl.getAnnotation();
	}

	/**
	 * Sets the excel column via a configurator lambda.
	 *
	 * @param consumer the column configurator
	 */
	public void setExcelColumn(Consumer<ExcelColumnImpl> consumer) {
		ExcelColumnImpl impl = new ExcelColumnImpl();
		consumer.accept(impl);
		this.excelColumn = impl.getAnnotation();
	}

	/**
	 * Sets the excel date via a configurator lambda.
	 *
	 * @param consumer the date configurator
	 */
	public void setExcelDate(Consumer<ExcelDateImpl> consumer) {
		ExcelDateImpl impl = new ExcelDateImpl();
		consumer.accept(impl);
		this.excelDate = impl.getAnnotation();
	}

	/**
	 * Sets the excel data validation via a configurator lambda.
	 *
	 * @param consumer the data validation configurator
	 */
	public void setExcelDataValidation(Consumer<ExcelDataValidationImpl> consumer) {
		ExcelDataValidationImpl impl = new ExcelDataValidationImpl();
		consumer.accept(impl);
		this.excelDataValidation = impl.getAnnotation();
	}

	/**
	 * Sets the excel column width via a configurator lambda.
	 *
	 * @param consumer the column width configurator
	 */
	public void setExcelColumnWidth(Consumer<ExcelColumnWidthImpl> consumer) {
		ExcelColumnWidthImpl impl = new ExcelColumnWidthImpl();
		consumer.accept(impl);
		this.excelColumnWidth = impl.getAnnotation();
	}

	/**
	 * Sets the excel merge row via a configurator lambda.
	 *
	 * @param consumer the merge row configurator
	 */
	public void setExcelMergeRow(Consumer<ExcelMergeRowImpl> consumer) {
		ExcelMergeRowImpl impl = new ExcelMergeRowImpl();
		consumer.accept(impl);
		this.excelMergeRow = impl.getAnnotation();
	}

	/**
	 * Sets the excel function via a configurator lambda.
	 *
	 * @param consumer the function configurator
	 */
	public void setExcelFunction(Consumer<ExcelFunctionImpl> consumer) {
		ExcelFunctionImpl impl = new ExcelFunctionImpl();
		consumer.accept(impl);
		this.excelFunction = impl.getAnnotation();
	}

	/**
	 * Sets the excel header cell layout via a configurator lambda.
	 *
	 * @param consumer the header cell layout configurator
	 */
	public void setExcelHeaderCellLayout(Consumer<ExcelHeaderCellLayoutImpl> consumer) {
		ExcelHeaderCellLayoutImpl impl = new ExcelHeaderCellLayoutImpl();
		consumer.accept(impl);
		this.excelHeaderCellLayout = impl.getAnnotation();
	}

	/**
	 * Sets the excel drop down via a configurator lambda.
	 *
	 * @param consumer the drop down configurator
	 */
	public void setExcelDropDown(Consumer<ExcelDropDownImpl> consumer) {
		ExcelDropDownImpl impl = new ExcelDropDownImpl();
		consumer.accept(impl);
		this.excelDropDown = impl.getAnnotation();
	}

	/**
	 * Sets the excel subtotal via a configurator lambda.
	 *
	 * @param consumer the subtotal configurator
	 */
	public void setExcelSubtotal(Consumer<ExcelSubtotalImpl> consumer) {
		ExcelSubtotalImpl impl = new ExcelSubtotalImpl();
		consumer.accept(impl);
		this.excelSubtotal = impl.getAnnotation();
	}

	/**
	 * Sets the excel boolean text via a configurator lambda.
	 *
	 * @param consumer the boolean text configurator
	 */
	public void setExcelBooleanText(Consumer<ExcelBooleanTextImpl> consumer) {
		ExcelBooleanTextImpl impl = new ExcelBooleanTextImpl();
		consumer.accept(impl);
		this.excelBooleanText = impl.getAnnotation();
	}

	/**
	 * Sets the excel number format via a configurator lambda.
	 *
	 * @param consumer the number format configurator
	 */
	public void setExcelNumberFormat(Consumer<ExcelNumberFormatImpl> consumer) {
		ExcelNumberFormatImpl impl = new ExcelNumberFormatImpl();
		consumer.accept(impl);
		this.excelNumberFormat = impl.getAnnotation();
	}

	/**
	 * Sets the excel image via a configurator lambda.
	 *
	 * @param consumer the image configurator
	 */
	public void setExcelImage(Consumer<ExcelImageImpl> consumer) {
		ExcelImageImpl impl = new ExcelImageImpl();
		consumer.accept(impl);
		this.excelImage = impl.getAnnotation();
	}

	/**
	 * Creates a new {@link Builder} starting from the cell layout configuration.
	 *
	 * @param consumer the cell layout configurator
	 * @return the builder
	 */
	public static Builder cellLayout(Consumer<ExcelCellLayoutImpl> consumer) {
		return new Builder().cellLayout(consumer);
	}

	/**
	 * Creates a new {@link Builder} starting from the column configuration.
	 *
	 * @param consumer the column configurator
	 * @return the builder
	 */
	public static Builder column(Consumer<ExcelColumnImpl> consumer) {
		return new Builder().column(consumer);
	}

	/**
	 * The Class Builder.<br>
	 * Fluent builder for {@link ExtraColumnAnnotation}.<br>
	 * {@code excelColumn} and {@code excelCellLayout} are required; all other fields are optional.
	 *
	 * <pre>
	 * ExtraColumnAnnotation annotation = ExtraColumnAnnotation
	 *     .cellLayout(layout -&gt; {
	 *         layout.setPrecision(2);
	 *         layout.setHorizontalAlignment(HorizontalAlignment.RIGHT);
	 *     })
	 *     .column(col -&gt; {
	 *         col.setName("Prezzo");
	 *         col.setIndex(5);
	 *     })
	 *     .dateFormat(date -&gt; date.setValue("dd/MM/yyyy"))
	 *     .build();
	 * </pre>
	 */
	public static class Builder {

		/** The target. */
		private final ExtraColumnAnnotation target = new ExtraColumnAnnotation();

		/**
		 * Cell layout.
		 *
		 * @param consumer the consumer
		 * @return the builder
		 */
		public Builder cellLayout(Consumer<ExcelCellLayoutImpl> consumer) {
			ExcelCellLayoutImpl impl = new ExcelCellLayoutImpl();
			consumer.accept(impl);
			target.setExcelCellLayout(impl);
			return this;
		}

		/**
		 * Column.
		 *
		 * @param consumer the consumer
		 * @return the builder
		 */
		public Builder column(Consumer<ExcelColumnImpl> consumer) {
			ExcelColumnImpl impl = new ExcelColumnImpl();
			consumer.accept(impl);
			target.setExcelColumn(impl);
			return this;
		}

		/**
		 * Date format.
		 *
		 * @param consumer the consumer
		 * @return the builder
		 */
		public Builder dateFormat(Consumer<ExcelDateImpl> consumer) {
			ExcelDateImpl impl = new ExcelDateImpl();
			consumer.accept(impl);
			target.setExcelDate(impl);
			return this;
		}

		/**
		 * Column width.
		 *
		 * @param consumer the consumer
		 * @return the builder
		 */
		public Builder columnWidth(Consumer<ExcelColumnWidthImpl> consumer) {
			ExcelColumnWidthImpl impl = new ExcelColumnWidthImpl();
			consumer.accept(impl);
			target.setExcelColumnWidth(impl);
			return this;
		}

		/**
		 * Header cell layout.
		 *
		 * @param consumer the consumer
		 * @return the builder
		 */
		public Builder headerCellLayout(Consumer<ExcelHeaderCellLayoutImpl> consumer) {
			ExcelHeaderCellLayoutImpl impl = new ExcelHeaderCellLayoutImpl();
			consumer.accept(impl);
			target.setExcelHeaderCellLayout(impl);
			return this;
		}

		/**
		 * Merge row.
		 *
		 * @param consumer the consumer
		 * @return the builder
		 */
		public Builder mergeRow(Consumer<ExcelMergeRowImpl> consumer) {
			ExcelMergeRowImpl impl = new ExcelMergeRowImpl();
			consumer.accept(impl);
			target.setExcelMergeRow(impl);
			return this;
		}

		/**
		 * Function.
		 *
		 * @param consumer the consumer
		 * @return the builder
		 */
		public Builder function(Consumer<ExcelFunctionImpl> consumer) {
			ExcelFunctionImpl impl = new ExcelFunctionImpl();
			consumer.accept(impl);
			target.setExcelFunction(impl);
			return this;
		}

		/**
		 * Drop down.
		 *
		 * @param consumer the consumer
		 * @return the builder
		 */
		public Builder dropDown(Consumer<ExcelDropDownImpl> consumer) {
			ExcelDropDownImpl impl = new ExcelDropDownImpl();
			consumer.accept(impl);
			target.setExcelDropDown(impl);
			return this;
		}

		/**
		 * Subtotal.
		 *
		 * @param consumer the consumer
		 * @return the builder
		 */
		public Builder subtotal(Consumer<ExcelSubtotalImpl> consumer) {
			ExcelSubtotalImpl impl = new ExcelSubtotalImpl();
			consumer.accept(impl);
			target.setExcelSubtotal(impl);
			return this;
		}

		/**
		 * Boolean text.
		 *
		 * @param consumer the consumer
		 * @return the builder
		 */
		public Builder booleanText(Consumer<ExcelBooleanTextImpl> consumer) {
			ExcelBooleanTextImpl impl = new ExcelBooleanTextImpl();
			consumer.accept(impl);
			target.setExcelBooleanText(impl);
			return this;
		}

		/**
		 * Data validation.
		 *
		 * @param consumer the consumer
		 * @return the builder
		 */
		public Builder dataValidation(Consumer<ExcelDataValidationImpl> consumer) {
			ExcelDataValidationImpl impl = new ExcelDataValidationImpl();
			consumer.accept(impl);
			target.setExcelDataValidation(impl);
			return this;
		}

		/**
		 * Number format.
		 *
		 * @param consumer the consumer
		 * @return the builder
		 */
		public Builder numberFormat(Consumer<ExcelNumberFormatImpl> consumer) {
			ExcelNumberFormatImpl impl = new ExcelNumberFormatImpl();
			consumer.accept(impl);
			target.setExcelNumberFormat(impl);
			return this;
		}

		/**
		 * Image.
		 *
		 * @param consumer the consumer
		 * @return the builder
		 */
		public Builder image(Consumer<ExcelImageImpl> consumer) {
			ExcelImageImpl impl = new ExcelImageImpl();
			consumer.accept(impl);
			target.setExcelImage(impl);
			return this;
		}

		/**
		 * Builds the {@link ExtraColumnAnnotation}.<br>
		 * Throws {@link IllegalStateException} if {@code excelColumn} or {@code excelCellLayout} are not set.
		 *
		 * @return the extra column annotation
		 */
		public ExtraColumnAnnotation build() {
			if (target.excelColumn == null)
				throw new IllegalStateException("excelColumn is required");
			if (target.excelCellLayout == null)
				throw new IllegalStateException("excelCellLayout is required");
			return target;
		}
	}

	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((excelCellLayout == null) ? 0 : excelCellLayout.hashCode());
		result = prime * result + ((excelColumn == null) ? 0 : excelColumn.hashCode());
		result = prime * result + ((excelColumnWidth == null) ? 0 : excelColumnWidth.hashCode());
		result = prime * result + ((excelDate == null) ? 0 : excelDate.hashCode());
		result = prime * result + ((excelDropDown == null) ? 0 : excelDropDown.hashCode());
		result = prime * result + ((excelFunction == null) ? 0 : excelFunction.hashCode());
		result = prime * result + ((excelHeaderCellLayout == null) ? 0 : excelHeaderCellLayout.hashCode());
		result = prime * result + ((excelImage == null) ? 0 : excelImage.hashCode());
		result = prime * result + ((excelMergeRow == null) ? 0 : excelMergeRow.hashCode());
		result = prime * result + ((excelNumberFormat == null) ? 0 : excelNumberFormat.hashCode());
		result = prime * result + ((excelSubtotal == null) ? 0 : excelSubtotal.hashCode());
		return result;
	}

	/**
	 * Equals.
	 *
	 * @param obj the obj
	 * @return true, if successful
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ExtraColumnAnnotation other = (ExtraColumnAnnotation) obj;
		if (excelCellLayout == null) {
			if (other.excelCellLayout != null)
				return false;
		} else if (!excelCellLayout.equals(other.excelCellLayout))
			return false;
		if (excelColumn == null) {
			if (other.excelColumn != null)
				return false;
		} else if (!excelColumn.equals(other.excelColumn))
			return false;
		if (excelColumnWidth == null) {
			if (other.excelColumnWidth != null)
				return false;
		} else if (!excelColumnWidth.equals(other.excelColumnWidth))
			return false;
		if (excelDate == null) {
			if (other.excelDate != null)
				return false;
		} else if (!excelDate.equals(other.excelDate))
			return false;
		if (excelDropDown == null) {
			if (other.excelDropDown != null)
				return false;
		} else if (!excelDropDown.equals(other.excelDropDown))
			return false;
		if (excelFunction == null) {
			if (other.excelFunction != null)
				return false;
		} else if (!excelFunction.equals(other.excelFunction))
			return false;
		if (excelHeaderCellLayout == null) {
			if (other.excelHeaderCellLayout != null)
				return false;
		} else if (!excelHeaderCellLayout.equals(other.excelHeaderCellLayout))
			return false;
		if (excelImage == null) {
			if (other.excelImage != null)
				return false;
		} else if (!excelImage.equals(other.excelImage))
			return false;
		if (excelMergeRow == null) {
			if (other.excelMergeRow != null)
				return false;
		} else if (!excelMergeRow.equals(other.excelMergeRow))
			return false;
		if (excelNumberFormat == null) {
			if (other.excelNumberFormat != null)
				return false;
		} else if (!excelNumberFormat.equals(other.excelNumberFormat))
			return false;
		if (excelSubtotal == null) {
			if (other.excelSubtotal != null)
				return false;
		} else if (!excelSubtotal.equals(other.excelSubtotal))
			return false;
		return true;
	}

}
