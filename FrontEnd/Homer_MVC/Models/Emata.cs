namespace Homer_MVC.Models
{
    using System;
    using System.Data.Entity;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Linq;

    public partial class Emata : DbContext
    {
        public Emata()
            : base("name=EmataContext")
        {
        }

        public virtual DbSet<Trash> Trashes { get; set; }

        protected override void OnModelCreating(DbModelBuilder modelBuilder)
        {
        }
    }
}
