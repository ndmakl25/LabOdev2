import java.util.Scanner;

class Eleman {
    String ad;
    String soyad;
    String dogumTarihi;
    String telefonNo;

    public Eleman(String ad, String soyad, String dogumTarihi, String telefonNo) {
        this.ad = ad;
        this.soyad = soyad;
        this.dogumTarihi = dogumTarihi;
        this.telefonNo = telefonNo;
    }

    @Override
    public String toString() {
        return "Ad: " + ad + ", Soyad: " + soyad + ", Doğum Tarihi: " + dogumTarihi + ", Telefon No: " + telefonNo;
    }
}

class BagliListe {
    private Node head;

    private class Node {
        Eleman eleman;
        Node next;

        Node(Eleman eleman) {
            this.eleman = eleman;
        }
    }

    public void listeBasinaEkle(Eleman eleman) {
        Node newNode = new Node(eleman);
        newNode.next = head;
        head = newNode;
    }

    public void listeElemaniSil(String arananIcerik) {
        Node current = head;
        Node previous = null;

        while (current != null && !elemanIcerikEslesiyor(current.eleman, arananIcerik)) {
            previous = current;
            current = current.next;
        }

        if (current != null) {
            if (previous != null) {
                previous.next = current.next;
            } else {
                head = current.next;
            }
        }
    }

    public Eleman listeElemaniAra(String arananIcerik) {
        Node current = head;

        while (current != null && !elemanIcerikEslesiyor(current.eleman, arananIcerik)) {
            current = current.next;
        }

        return current != null ? current.eleman : null;
    }

    public void tumElemanlariListele() {
        if (head == null) {
            System.out.println("Liste boş.");
            return;
        }

        Node current = head;
        while (current != null) {
            System.out.println(current.eleman.toString());
            current = current.next;
        }
    }

    private boolean elemanIcerikEslesiyor(Eleman eleman, String arananIcerik) {
        return eleman.ad.contains(arananIcerik)
                || eleman.soyad.contains(arananIcerik)
                || eleman.dogumTarihi.contains(arananIcerik)
                || eleman.telefonNo.contains(arananIcerik);
    }
}

public class Data {
    public static void main(String[] args) {
        BagliListe bagliListe = new BagliListe();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Liste Başına Ekle");
            System.out.println("2. Liste Elemanını Sil");
            System.out.println("3. Liste Elemanını Ara");
            System.out.println("4. Tüm Elemanları Listele");
            System.out.println("5. Çıkış");

            System.out.print("Seçenek: ");
            int secim = scanner.nextInt();
            scanner.nextLine(); // Dummy nextLine to consume the newline character

            switch (secim) {
                case 1:
                    System.out.print("Ad: ");
                    String ad = scanner.nextLine();
                    System.out.print("Soyad: ");
                    String soyad = scanner.nextLine();
                    System.out.print("Doğum Tarihi: ");
                    String dogumTarihi = scanner.nextLine();
                    System.out.print("Telefon No: ");
                    String telefonNo = scanner.nextLine();

                    bagliListe.listeBasinaEkle(new Eleman(ad, soyad, dogumTarihi, telefonNo));
                    break;

                case 2:
                    System.out.print("Silinecek Elemanın İçeriği: ");
                    String silinecekIcerik = scanner.nextLine();
                    bagliListe.listeElemaniSil(silinecekIcerik);
                    break;

                case 3:
                    System.out.print("Aranacak Elemanın İçeriği: ");
                    String aranacakIcerik = scanner.nextLine();
                    Eleman bulunanEleman = bagliListe.listeElemaniAra(aranacakIcerik);

                    if (bulunanEleman != null) {
                        System.out.println("Bulunan Eleman: " + bulunanEleman.toString());
                    } else {
                        System.out.println("Eleman bulunamadı.");
                    }
                    break;

                case 4:
                    bagliListe.tumElemanlariListele();
                    break;

                case 5:
                    System.out.println("Çıkış yapılıyor.");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Geçersiz seçenek!");
            }
        }
    }
}