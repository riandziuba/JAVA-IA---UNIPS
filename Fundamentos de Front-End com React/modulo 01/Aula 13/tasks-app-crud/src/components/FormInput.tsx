import { Dispatch, FC, InputHTMLAttributes, SetStateAction } from "react";

interface FormInputProps extends InputHTMLAttributes<HTMLInputElement> {
  label: String,
  setValue: Dispatch<SetStateAction<string>>
}

export const FormInput: FC<FormInputProps> = ({ id, label, value, setValue, ...inputProps }) => (
  <fieldset className="grid">
    <label className="text-[#7b7c7b]" htmlFor={id}>{label}</label>
    <input type="text"
      className="px-2 py-1 text-[#7b7c7b] border border-[#e8e9e9] focus:border-[#b1b2b2] hover:border-[#b1b2b2] outline-none shadow-md rounded-lg"
      name={id}
      id={id}
      value={value}
      onChange={(e) => { setValue(e.target.value) }}
      {...inputProps}
    />
  </fieldset>
);
