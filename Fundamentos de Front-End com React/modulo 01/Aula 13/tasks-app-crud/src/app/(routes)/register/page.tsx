import Link from "next/link";
import { checkInvalidEmail, checkInvalidPassword } from "../../../lib/utils";
import { redirect } from "next/navigation";
import { FormRegister } from "../../../components/forms/FormRegister";
import { cookies } from "next/headers";
import { Metadata } from "next";
import { COOKIE_SET } from "@/src/constants/constants";

const PAGE_TITLE = "Cadastro"

export const metadata: Metadata = {
  title: PAGE_TITLE
}

export default function Register() {
  const handleRegister = async (_: string, formData: FormData) => {
    "use server";
    const username = formData.get("username")?.toString();
    const email = formData.get("email")?.toString();
    const password = formData.get("password")?.toString();

    if (!username || !email || !password) return "Preencha os campos";

    if (checkInvalidEmail(email)) return "Email inválido"

    if (checkInvalidPassword(password)) return "Senha precisa ter no mínimo 6 caracteres"

    try {
      const body = {
        username,
        email,
        password
      }


      const res = await fetch(`${process.env.BACKEND_URL}/auth/register`, {
        method: "POST",
        body: JSON.stringify(body),
        headers: {
          'Content-type': 'application/json'
        }
      })

      const { token, message } = await res.json();

      if (!token) return message;

      const cookieStore = await cookies();
      cookieStore.set("token", token, COOKIE_SET)

    } catch {
      console.error('handleRegister failed')

      return 'Erro no cadastro'
    }
    redirect('/tasks')
  }

  return (
    <>
      <h1 className="text-4xl text-center font-bold">{PAGE_TITLE}</h1>
      <FormRegister action={handleRegister} />
      <Link className="text-center underline" href="/login">Já tenho cadastro</Link>
    </>
  );
}
