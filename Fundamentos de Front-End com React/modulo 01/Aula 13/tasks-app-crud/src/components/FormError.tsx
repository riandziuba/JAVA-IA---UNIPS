"use client"
import { FC, useState } from "react";
import { FormInput } from "./FormInput";

type FormErrorProps = {
  message: string
}

export const FormError: FC<FormErrorProps> = ({ message }) => {
  if (!message) return null;
  return (
    <p className="px-4 py-2 bg-red-400 text-white font-bold text-sm rounded-lg">
      {message}
    </p>
  )
}
