
import { Hobbies } from "@/components/Hobbies";
import { ImageComponent } from "@/components/ImageComponent";
import { MyName } from "@/components/MyName";
import Link from "next/link";
// arrow function
// export default function Page() {
//   return (
//     <div>Page</div>
//   );
// }
type myPageParams = {
  params: Promise<{
    name: string
  }>
}


const Page = async ({ params }: myPageParams) => {
  const { name } = await params;

  return (
    <div className="grid gap-y-4">
      <MyName name={name} age={24} birthDate={new Date(2002, 0, 12)} />
      <Hobbies />
      <div>
        <p>Estou Aprendendo:</p>
        <ImageComponent />
      </div>
      <Link className="underline" href="/level-0">Voltar</Link>
    </div>
  )
};


export default Page;
