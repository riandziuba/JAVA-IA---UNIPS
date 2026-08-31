import { FormLogin } from "@/src/components/forms/FormLogin";
import { COOKIE_SET } from "@/src/constants/constants";
import { checkInvalidEmail, checkInvalidPassword } from "@/src/lib/utils";
import { Metadata } from "next";
import { redirect } from "next/dist/client/components/navigation";
import Link from "next/dist/client/link";
import { cookies } from "next/headers";

const PAGE_TITLE = "Login"


export const metadata: Metadata = {
  title: PAGE_TITLE
}

export default function Login() {

  const handleLogin = async (_: string, formData: FormData) => {
    "use server";
    const email = formData.get("email")?.toString();
    const password = formData.get("password")?.toString();

    if (!email || !password) return "Preencha os campos";

    if (checkInvalidEmail(email)) return "Email inválido"

    if (checkInvalidPassword(password)) return "Senha precisa ter no mínimo 6 caracteres"

    try {
      const body = {
        email,
        password
      }


      const res = await fetch(`${process.env.BACKEND_URL}/auth/login`, {
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
      console.error('handleLogin failed')

      return 'Erro no login'
    }
    redirect('/tasks')
  }

  return (
    <>
      <h1 className="text-4xl text-center font-bold">{PAGE_TITLE}</h1>

      <FormLogin action={handleLogin}></FormLogin>

      <Link className="text-center underline" href="/register">Não tenho cadastro</Link>
    </>
  );
}
