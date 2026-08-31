"use client"
import { FC, useActionState, useState } from "react";
import { FormInput } from "../FormInput";
import { FormButton } from "../FormButton";
import { FormError } from "../FormError";

type FormRegisterProps = {
  action: (_: string, formData: FormData) => Promise<string>
}

export const FormRegister: FC<FormRegisterProps> = ({ action }) => {
  const [username, setUsername] = useState("");
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
        <FormInput id="username" value={username} label="Usuário" setValue={setUsername}></FormInput>
        <FormInput id="email" value={email} label={"Email"} setValue={setEmail}></FormInput>
        <FormInput id="password" value={password} label={"Senha"} setValue={setPassword} type="password"></FormInput>

        {/* <fieldset className="grid">
        <label className="text-[#7b7c7b]" htmlFor="password">Senha</label>
        <div className="relative flex items-center">
        <input type={showPassword ? "text" : "password"}
        className="w-full pl-2 pr-9 py-1 text-[#7b7c7b] border border-[#e8e9e9] focus:border-[#b1b2b2] hover:border-[#b1b2b2] outline-none shadow-md rounded-lg"
        name="password"
        id="password"
        value={password}
        onChange={(e) => { setPassword(e.target.value) }}
        />
        <button
        className="cursor-pointer absolute right-2"
        type="button"
        onClick={() => { setShowPassword(!showPassword) }}>👀</button>
        </div>
        </fieldset> */ /*Just show password code for example*/}
        <FormButton>Cadastrar</FormButton>
      </form>
    </>
  );
}
