"use client"
import { FC, useActionState, useState } from "react";
import { FormInput } from "../FormInput";
import { FormButton } from "../FormButton";
import { FormError } from "../FormError";

type FormLoginProps = {
  action: (_: string, formData: FormData) => Promise<string>
}

export const FormLogin: FC<FormLoginProps> = ({ action }) => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [showPassword, setShowPassword] = useState(false);

  const [errorMessage, formAction, isPending] = useActionState(action, "")

  return (
    <>
      {
        !isPending && (
          <FormError message={errorMessage}></FormError>
        )
      }
      <form className="grid gap-y-6" action={formAction}>
        <FormInput id="email" value={email} label={"Email"} setValue={setEmail}></FormInput>
        <FormInput id="password" value={password} label={"Senha"} setValue={setPassword} type="password"></FormInput>

        <FormButton>Login</FormButton>
      </form>
    </>
  );
}
